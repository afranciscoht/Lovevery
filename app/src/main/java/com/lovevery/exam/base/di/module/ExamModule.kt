package com.lovevery.exam.base.di.module

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.lovevery.exam.base.di.providers.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(
    includes = [
        ExamServicesModule::class
    ]
)
class ExamModule {

    @Provides
    @Reusable
    fun provideGson(): Gson = Gson()

    @Provides
    @Reusable
    fun provideResourceProvider(context: Context) = object : ResourceProvider {

        override fun getString(id: Int): String = context.getString(id)

        override fun getInteger(id: Int): Int = context.resources.getInteger(id)

        override fun getString(id: Int, vararg args: Any?): String =
            context.getString(id, *args)

        override fun getColor(colorRes: Int): Int = ContextCompat.getColor(context, colorRes)

        override fun getDrawable(drawableRes: Int): Drawable {
            return requireNotNull(ContextCompat.getDrawable(context, drawableRes))
        }
    }
}