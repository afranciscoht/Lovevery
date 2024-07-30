package com.lovevery.exam.utils.extensions

import android.content.res.ColorStateList
import android.content.res.TypedArray

inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, resourceHandler: (T) -> Unit) {
    val value = getInt(index, -1)
    if (value >= 0) {
        resourceHandler(enumValues<T>()[value])
    }
}

inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T): T {
    val value = getInt(index, -1)
    return if (value >= 0) {
        enumValues<T>()[value]
    } else {
        default
    }
}

fun TypedArray.getResourceIdOrNull(index: Int): Int? {
    return getResourceId(index, -1).takeIf { it >= 0 }
}

fun TypedArray.getResourceId(index: Int, resourceHandler: (Int) -> Unit) {
    getResourceId(index, -1)
        .takeIf { it >= 0 }
        ?.let(resourceHandler)
}

fun TypedArray.getColorStateList(index: Int, resourceHandler: (ColorStateList) -> Unit) {
    getColorStateList(index)?.let(resourceHandler)
}

fun TypedArray.getBoolean(index: Int) = getBoolean(index, false)

fun TypedArray.getResourceId(index: Int) = getResourceId(index, 0)

inline fun TypedArray.use(block: TypedArray.() -> Unit) {
    block().also {
        recycle()
    }
}