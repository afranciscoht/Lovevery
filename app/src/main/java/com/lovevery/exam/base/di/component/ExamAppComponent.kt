package com.lovevery.exam.base.di.component

import androidx.fragment.app.FragmentActivity
import com.lovevery.exam.base.di.module.ExamModule
import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ExamComponent::class],
    modules = [
        AndroidSupportInjectionModule::class,
        ExamModule::class
    ]
)
interface ExamAppComponent : ExamViewModelsProvider {

    fun androidInjector(): DispatchingAndroidInjector<Any>

    @Component.Factory
    interface Factory {
        fun create(
            parentProvider: ExamComponent
        ): ExamAppComponent
    }
}

val FragmentActivity.injector
    get() = DaggerExamAppComponent.factory()
        .create((application as ExamComponentProvider).provideAppComponent())

fun FragmentActivity.inject() {
    DaggerExamAppComponent.factory()
        .create((application as ExamComponentProvider).provideAppComponent())
        .androidInjector()
        .inject(this)
}