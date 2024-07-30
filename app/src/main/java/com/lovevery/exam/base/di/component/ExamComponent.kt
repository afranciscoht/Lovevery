package com.lovevery.exam.base.di.component

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.lovevery.exam.base.preferences.BasePreferences

interface ExamComponent {

    fun context(): Context

    fun application(): Application

    fun gson(): Gson

    fun sharedPreferences(): BasePreferences
}