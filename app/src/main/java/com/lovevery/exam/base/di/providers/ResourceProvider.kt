package com.lovevery.exam.base.di.providers

import android.graphics.drawable.Drawable
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes id: Int): String

    fun getInteger(@IntegerRes id: Int): Int

    fun getString(@StringRes id: Int, vararg args: Any?): String

    fun getColor(colorRes: Int): Int

    fun getDrawable(drawableRes: Int): Drawable
}