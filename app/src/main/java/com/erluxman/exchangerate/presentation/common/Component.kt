package com.erluxman.exchangerate.presentation.common

import com.erluxman.exchangerate.ui.common.BaseView
import com.spotify.mobius.EventSource
import com.spotify.mobius.First
import com.spotify.mobius.Next
import io.reactivex.Observable
import io.reactivex.ObservableTransformer


data class Component<M, E, F>(
    val initFun: (M) -> First<M, F> = { model -> First.first(model) },
    val updateFun: (M, E) -> Next<M, F> = { _, _ -> Next.noChange<M, F>() },
    val effectFun: ObservableTransformer<F, E> = SubtypeEffectHandlerBuilder<F, E>().build(),
    val eventSource: EventSource<E> = DeferredEventSource(),
    val connectToViewFun: (stateStream: Observable<M>) -> Observable<E> = { Observable.empty<E>() },
    val view: BaseView<M, E>,
    val defaultScreenModel: M
) {
    fun initLayout(model: M) {
        view.initLayout(model)
    }
}