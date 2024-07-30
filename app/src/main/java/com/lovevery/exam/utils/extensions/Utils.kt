package com.lovevery.exam.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Html
import android.text.Spanned

fun Context.launchActivity(clazz: Class<out Activity>, addFlags: Boolean? = false) = startActivity(
    Intent(this, clazz).apply {
        if (addFlags.orTrue()) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
)

@SuppressLint("NewApi")
fun fromHtml(source: String?): Spanned {
    return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
}

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true