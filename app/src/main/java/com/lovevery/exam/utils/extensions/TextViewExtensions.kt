package com.lovevery.exam.utils.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isGone
import androidx.core.widget.TextViewCompat
import com.google.android.material.textview.MaterialTextView

fun TextView.setTextColorResource(@ColorRes color: Int) = setTextColor(getColor(color))

fun AppCompatTextView.setCompoundDrawable(
    left: Drawable? = null,
    top: Drawable? = null,
    right: Drawable? = null,
    bottom: Drawable? = null
) = setCompoundDrawables(left, top, right, bottom)

fun TextView.setTextAndVisibility(textValue: CharSequence?) {
    text = textValue
    isGone = textValue.isNullOrBlank()
}

fun MaterialTextView.setTextAndVisibility(@StringRes textRes: Int) {
    if (textRes == View.NO_ID || textRes == 0) {
        text = null
    } else {
        setText(textRes)
    }
    isGone = text.isNullOrBlank()
}

fun TextView.setAutoSizeTextType(
    autoSizeTextType: Int = TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
) {
    TextViewCompat.setAutoSizeTextTypeWithDefaults(this, autoSizeTextType)
}

fun TextView.setTextResourceIfNotZero(@StringRes textResource: Int) {
    if (textResource != 0) setText(textResource)
}