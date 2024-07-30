package com.lovevery.exam.utils.extensions

import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.lovevery.exam.R

enum class TextStyle(@StyleRes internal val textAppearance: Int) {
    LABEL_REGULAR(R.style.Lovevery_Text_LabelRegular)
}

fun TextView.setTextAppearance(textStyle: TextStyle) {
    TextViewCompat.setTextAppearance(this, textStyle.textAppearance)
}