package com.lovevery.exam.base.application

import android.content.Context
import androidx.multidex.MultiDex
import com.lovevery.exam.base.di.component.BaseComponent

abstract class ExamApplication : InjectableApplication() {

    var component: BaseComponent? = null
        protected set

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var instance: ExamApplication? = null
            private set
    }
}