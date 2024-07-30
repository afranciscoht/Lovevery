package com.lovevery.exam.base.di.module

import android.app.Application
import android.content.Context
import com.lovevery.exam.base.application.InjectableApplication
import com.lovevery.exam.base.preferences.BasePreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ExamAppModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesContext(app: InjectableApplication): Context = app.applicationContext

    @Provides
    @Singleton
    @JvmStatic
    fun providesApplication(app: InjectableApplication): Application = app

    @Provides
    @Singleton
    @JvmStatic
    fun providesSharedPreferences(context: Context) = BasePreferences(context)
}