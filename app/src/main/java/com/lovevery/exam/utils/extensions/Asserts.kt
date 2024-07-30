package com.lovevery.exam.utils.extensions


import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows

fun <T : Throwable> assertThrowsWithMessage(
    expectedThrowable: Class<T>,
    expectedMessage: String,
    lambda: () -> Unit
) {
    val exception = assertThrows(expectedThrowable) { lambda() }
    assertEquals(expectedMessage, exception.message)
}

fun assertThrowsIllegalArgumentExceptionWithMessage(expectedMessage: String, lambda: () -> Unit) {
    assertThrowsWithMessage(IllegalArgumentException::class.java, expectedMessage, lambda)
}