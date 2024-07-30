package com.lovevery.exam.utils.extensions

import android.os.Handler
import android.os.Looper
import java.io.File

fun Any.className(): String = this::class.java.name
fun Any.simpleClassName(): String = this::class.java.simpleName

@Suppress("TooGenericExceptionCaught")
inline fun <T> tryOrDefault(f: () -> T, defaultValue: T): T {
    return try {
        f()
    } catch (e: Exception) {
        e.printStackTrace()
        defaultValue
    }
}

inline fun <T> tryOrNull(f: () -> T): T? = tryOrDefault(f, null)

@Suppress("TooGenericExceptionCaught")
inline fun <T> tryOrDefaultWithOnError(f: () -> T, onError: () -> Unit, defaultValue: T): T {
    return try {
        f()
    } catch (e: Exception) {
        e.printStackTrace()
        onError()
        defaultValue
    }
}

@Suppress("TooGenericExceptionCaught")
inline fun tryWithOnError(f: () -> Unit, onError: (e: Exception) -> Unit) {
    return try {
        f()
    } catch (e: Exception) {
        e.printStackTrace()
        onError(e)
    }
}

@Suppress("TooGenericExceptionCaught")
inline fun tryWithFinally(f: () -> Unit, finally: () -> Unit) {
    return try {
        f()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        finally()
    }
}

@Suppress("TooGenericExceptionCaught")
inline fun tryOrPrintException(f: () -> Unit) {
    return try {
        f()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun runWithDelay(delayInMillis: Int, crossinline f: () -> Unit) {
    Handler().postDelayed({ f() }, delayInMillis.toLong())
}

inline fun runWithDelayOnMainLooper(delayInMillis: Int, crossinline f: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ f() }, delayInMillis.toLong())
}

fun File.deleteWithoutFear() {
    if (exists()) {
        delete()
    }
}

fun CharSequence?.isNotNullOrBlank(): Boolean {
    return this.isNullOrBlank().not()
}

fun String?.isNotNullOrEmpty(): Boolean {
    return this.isNullOrEmpty().not()
}

fun <T> List<T>?.isNotNullOrEmpty(): Boolean {
    return this.isNullOrEmpty().not()
}

fun <K, V> Map<K, V?>.notNullOrEmpty(): Boolean =
    containsValue(null).not() && containsValue("" as V).not()

@Suppress("UNCHECKED_CAST")
inline fun <reified T> List<*>.asListOfType(): List<T>? =
    if (all { it is T }) this as List<T> else null