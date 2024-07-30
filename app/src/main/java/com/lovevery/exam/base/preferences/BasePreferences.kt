package com.lovevery.exam.base.preferences

import android.content.Context
import android.content.SharedPreferences

open class BasePreferences(context: Context) {

    protected lateinit var sharedPreferences: SharedPreferences

    init {
        init(context)
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    private fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    operator fun set(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    operator fun set(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    operator fun set(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String): String? = sharedPreferences.getString(key, null)

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    companion object {
        const val PREFERENCES_FILE_NAME = "ExamPreferences"
    }
}