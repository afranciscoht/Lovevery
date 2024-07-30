package com.lovevery.exam.utils.extensions

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.view.isVisible

fun ImageView.setDrawableAndVisibility(drawable: Drawable?) {
    setImageDrawable(drawable)
    isVisible = drawable != null
}

fun ImageView.setColorTint(
    @ColorInt color: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
) = setColorFilter(color, mode)

fun ImageView.setTintColorResource(
    @ColorRes color: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN
) = setColorTint(getColor(color), mode)