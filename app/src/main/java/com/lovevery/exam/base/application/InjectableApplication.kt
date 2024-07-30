package com.lovevery.exam.base.application

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class InjectableApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    /**
     * Dagger's `Has{Activity,Fragment,Service,ContentProvider,BroadcastReceiver}`
     * interfaces are now removed in favor of `HasAndroidInjector` (which can handle any type).
     */
    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    abstract fun initializeInjector()
}