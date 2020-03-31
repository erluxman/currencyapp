package com.erluxman.exchangerate.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.presentation.common.MobiusDelegate
import io.reactivex.disposables.CompositeDisposable

abstract class BaseMobiusFragment<M, E, F> : Fragment() {

    protected val disposable = CompositeDisposable()
    protected var savedInstanceState: Bundle = Bundle()
    private var mobiusDelegate: MobiusDelegate<M, E, F>? = null
    private var model: M? = null
    abstract fun getComponent(container: ViewGroup?): Component<M, E, F>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState
        }
        mobiusDelegate = MobiusDelegate()
        val component = getComponent(container)
        val savedModel: M = model ?: component.defaultScreenModel
        initMobius(component, savedModel)
        component.initLayout(savedModel)
        return component.view.containerView
    }

    private fun initMobius(component: Component<M, E, F>, model: M) {
        mobiusDelegate?.initMobius(
            component.initFun,
            component.updateFun,
            component.effectFun,
            component.eventSource,
            model
        )
        mobiusDelegate?.onCreate(component.connectToViewFun, model)
    }

    override fun onResume() {
        super.onResume()
        mobiusDelegate?.startLoop()
    }

    override fun onPause() {
        mobiusDelegate?.stopLoop()
        model = mobiusDelegate?.model
        super.onPause()
    }

    override fun onDestroyView() {
        mobiusDelegate?.disconnectLoop()
        mobiusDelegate = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}