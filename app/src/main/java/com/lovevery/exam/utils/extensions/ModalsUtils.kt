package com.lovevery.exam.utils.extensions

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.setImageAndVisibility(@DrawableRes iconId: Int) {
    if (iconId != 0) {
        setImageResource(iconId)
        show()
    } else {
        hide()
    }
}

fun ShapeableImageView.setImageAndVisibility(drawable: Drawable?) {
    setImageDrawable(drawable)
    if (drawable != null) {
        show()
    } else {
        hide()
    }
}