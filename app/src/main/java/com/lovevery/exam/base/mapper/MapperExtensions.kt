package com.lovevery.exam.base.mapper

fun String?.requireNotNullOrBlank(fieldName: String): String {
    return requireNotNull(fieldName).takeIf { it.isNotBlank() }
        ?: throw IllegalArgumentException("$fieldName cannot be blank or empty")
}