package com.erluxman.exchangerate.ui.utils

import android.content.Context
import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.erluxman.exchangerate.R
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer

fun View.visible(visible: Boolean?) {
    when (visible) {
        true -> this.visibility = VISIBLE
        false -> this.visibility = GONE
        else -> this.visibility = INVISIBLE
    }
}

fun View.gone() {
    if (visibility != GONE) {
        visibility = GONE
    }
}

fun View.visible() {
    if (visibility != VISIBLE) {
        visibility = VISIBLE
    }
}

fun View.inVisible() {
    if (visibility != INVISIBLE) {
        visibility = INVISIBLE
    }
}

fun View.isVisible() = this.visibility == VISIBLE


fun Boolean.toViewVisibility(): Int {
    return if (this) {
        VISIBLE
    } else {
        GONE
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun ImageView.loadImage(
    url: String = "",
    @DrawableRes drawable: Int = R.mipmap.ic_launcher_round,
    placeHolderUrl: String = "",
    @DrawableRes placeHolderDrawable: Int = R.mipmap.ic_launcher_round,
    circular: Boolean = false
) {
    val defDrawable = R.mipmap.ic_launcher_round

    val requestCreator = when {
        url.isNotEmpty() -> Picasso.get().load(url)
        placeHolderUrl.isNotEmpty() -> Picasso.get().load(placeHolderUrl)
        drawable != 0 -> Picasso.get().load(drawable)
        else -> Picasso.get().load(defDrawable) // error image
    }

    if (placeHolderDrawable != 0) {
        requestCreator.placeholder(placeHolderDrawable)
    }

    if (circular) {
        requestCreator.transform(CircleTransform())
    }

    requestCreator.into(this)
}

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Fragment.getColorCompat(color: Int) = this.context?.getColorCompat(color)

fun ViewPager.selectsPage(): Observable<Int> {
    val event = PublishSubject.create<Int>()
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            event.onNext(position)
        }
    })
    return event
}

fun LayoutContainer.toast(message: String) {
    if (containerView?.isShown!!) containerView?.context?.toast(message)
}

