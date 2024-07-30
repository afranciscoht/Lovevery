package com.lovevery.exam.base.di.component

import com.lovevery.exam.base.anvil.AppScope
import com.lovevery.exam.base.application.InjectableApplication
import com.lovevery.exam.base.di.module.ExamAppModule
import com.lovevery.exam.base.di.module.ExamModule
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@MergeComponent(
    scope = AppScope::class,
    modules = [
        AndroidSupportInjectionModule::class,
        ExamAppModule::class,
        ExamModule::class
    ]
)
interface MainComponent : BaseComponent, ExamComponent {

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        @BindsInstance
        fun application(application: InjectableApplication): Builder
    }
}