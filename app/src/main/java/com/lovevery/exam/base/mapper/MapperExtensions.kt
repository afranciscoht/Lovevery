package com.lovevery.exam.base.mapper

fun <T> T?.requireNotNull(fieldName: String): T {
    return this ?: throw IllegalArgumentException("$fieldName is required")
}

fun String?.requireNotNullOrBlank(fieldName: String): String {
    return requireNotNull(fieldName).takeIf { it.isNotBlank() }
        ?: throw IllegalArgumentException("$fieldName cannot be blank or empty")
}