package com.lovevery.exam.utils.extensions

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins

fun View.removeFromParent() {
    this.parent?.let {
        (it as ViewGroup).removeView(this)
    }
}

fun View.show(show: Boolean) {
    if (show) {
        show()
    } else {
        hide()
    }
}

fun View.showOrDisappear(show: Boolean) {
    if (show) {
        show()
    } else {
        disappear()
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.disappear() {
    this.visibility = View.INVISIBLE
}

fun View.updateHorizontalMargins(start: Int, end: Int = start) {
    (layoutParams as ViewGroup.MarginLayoutParams).updateMargins(
        left = start,
        right = end
    )
}

fun View.updateVerticalMargins(top: Int, bottom: Int = top) {
    (layoutParams as ViewGroup.MarginLayoutParams).updateMargins(
        top = top,
        bottom = bottom
    )
}

fun View.updateBottomMargin(bottom: Int) =
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        bottomMargin = bottom
    }

fun View.updateTopMargin(top: Int) =
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = top
    }

fun View.updateHeight(newHeight: Int) {
    updateLayoutParams {
        height = newHeight
    }
}

fun View.updateWidth(newWidth: Int) {
    updateLayoutParams {
        width = newWidth
    }
}

fun View.getColor(@ColorRes color: Int) = ContextCompat.getColor(context, color)

fun View.getColorStateList(@ColorRes color: Int) = ContextCompat.getColorStateList(context, color)

fun View.getStateListOfColor(@ColorRes color: Int) = ColorStateList.valueOf(getColor(color))

fun View.getDrawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(context, drawable)

fun View.setBackgroundColorResource(@ColorRes color: Int) = setBackgroundColor(getColor(color))

fun View.setBackgroundColorFilter(
    @ColorInt color: Int,
    blendMode: BlendModeCompat = BlendModeCompat.SRC
) {
    background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
        color,
        blendMode
    )
}

fun View.setDrawableColor(@ColorRes color: Int) = when (val drawable = background) {
    is ShapeDrawable -> drawable.paint.color = getColor(color)
    is GradientDrawable -> drawable.color = getStateListOfColor(color)
    is ColorDrawable -> drawable.color = getColor(color)
    else -> Unit
}