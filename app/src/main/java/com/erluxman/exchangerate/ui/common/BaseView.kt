package com.erluxman.exchangerate.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.extensions.LayoutContainer

abstract class BaseView<M, E>(val parent: ViewGroup?, private val layoutInflater: LayoutInflater? = null)
    : BaseViewContract<M, E> {
    private fun inflateLayout(@LayoutRes layoutRes: Int): View {
        return if (layoutInflater == null) LayoutInflater.from(parent?.context).inflate(layoutRes, parent, false)
        else layoutInflater.inflate(layoutRes, parent, false)
    }

    protected fun getContext(): Context = parent?.context!!
    protected fun getString(@StringRes stringId: Int): String = getContext().getString(stringId)
    override val containerView: View by lazy { inflateLayout(layout) }
    protected val disposable = CompositeDisposable()
    open fun initLayout(model: M?) {}
    @get:LayoutRes
    abstract val layout: Int
}

interface BaseViewContract<M, E> : LayoutContainer {
    fun bindView(model: Observable<M>): Observable<E>
}
