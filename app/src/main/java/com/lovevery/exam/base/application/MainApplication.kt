package com.lovevery.exam.base.application

import com.lovevery.exam.base.di.component.DaggerMainComponent
import com.lovevery.exam.base.di.component.MainComponent
import com.lovevery.exam.base.di.component.ExamComponentProvider

class MainApplication : ExamApplication(), ExamComponentProvider {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    private fun getMainComponent() = component as MainComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun initializeInjector() {
        val component = DaggerMainComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@MainApplication) }
        this.component = component
    }

    override fun provideAppComponent() = getMainComponent()
}