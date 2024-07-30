package com.lovevery.exam.utils.extensions

inline fun <reified T : Enum<T>> valueOf(type: String?, default: T): T {
    if (type == null) return default

    return tryOrDefault(
        {
            java.lang.Enum.valueOf(T::class.java, type)
        },
        default
    )
}

inline fun <reified T : Enum<T>> valueOfOrNull(type: String?): T? {
    if (type == null) return null

    return tryOrDefault(
        {
            java.lang.Enum.valueOf(T::class.java, type)
        },
        null
    )
}