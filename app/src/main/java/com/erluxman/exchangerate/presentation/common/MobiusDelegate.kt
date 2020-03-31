package com.erluxman.exchangerate.presentation.common

import com.spotify.mobius.*
import com.spotify.mobius.android.AndroidLogger
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.rx2.RxConnectables
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class MobiusDelegate<M, E, F> {

    private lateinit var loopController: MobiusLoop.Controller<M, E>
    val model: M
        get() = loopController.model

    fun initMobius(initFun: (M) -> First<M, F>,
                   updateFun: (model: M, event: E) -> Next<M, F>,
                   effectHandlerFun: ObservableTransformer<F, E>,
                   eventSource: EventSource<E>,
                   model: M,
                   logTag: String = "mobius-etracking"
    ) {
        val loop = createLoop(initFun, updateFun, effectHandlerFun, eventSource, logTag)
        initController(loop, model)
    }

    private fun createLoop(
            initFun: (M) -> First<M, F>,
            updateFun: (model: M, event: E) -> Next<M, F>,
            effectHandler: ObservableTransformer<F, E>,
            eventSource: EventSource<E>,
            logTag: String)
            : MobiusLoop.Factory<M, E, F> = loopFactory(updateFun, effectHandler)
            .init(initFun)
            .eventSource(eventSource)
            .logger(AndroidLogger.tag(logTag))

    private fun <M, E, F> loopFactory(u: (M, E) -> Next<M, F>, fh: ObservableTransformer<F, E>) = RxMobius.loop(updateWrapper(u), fh)
    private fun <M, E, F> updateWrapper(u: (M, E) -> Next<M, F>): Update<M, E, F> = Update { m: M, e: E -> u(m, e) }

    private fun initController(loop: MobiusLoop.Factory<M, E, F>, model: M) {
        loopController = MobiusAndroid.controller(loop, model)
    }

    fun onCreate(connectToViewFun: (Observable<M>) -> Observable<E>, model: M) {
        loopController.connect(RxConnectables.fromTransformer(connectToViewFun))
        loopController.replaceModel(model)
    }

    fun startLoop() {
        loopController.start()
    }

    fun stopLoop() {
        loopController.stop()
    }

    fun disconnectLoop() {
        loopController.disconnect()
    }
}