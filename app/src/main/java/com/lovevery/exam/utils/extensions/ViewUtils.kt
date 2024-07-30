package com.lovevery.exam.utils.extensions

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import com.lovevery.exam.R

object ViewUtils {

    data class DayNightContextParams(
        val forceDark: Boolean,
        val forceLight: Boolean
    )

    fun createDayNightThemedContext(context: Context, dark: Boolean): Context {
        val ctx = ContextThemeWrapper(context, R.style.Theme_Lovevery)
        val configuration = Configuration(context.resources.configuration).apply {
            uiMode = if (dark) Configuration.UI_MODE_NIGHT_YES else Configuration.UI_MODE_NIGHT_NO
        }
        ctx.applyOverrideConfiguration(configuration)
        return ctx
    }

    fun createDayNightThemedContext(context: Context, attrs: AttributeSet?): Context {
        if (attrs != null) {
            val dayNightParams = initAttrs(context, attrs)
            if (dayNightParams.forceDark || dayNightParams.forceLight) {
                return createDayNightThemedContext(context, dayNightParams.forceDark)
            }
        }
        return getThemeWrappedContext(context)
    }

    private fun initAttrs(context: Context, attributeSet: AttributeSet): DayNightContextParams {
        var forceDark = false
        var forceLight = false
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.Theme_lovevery_dark_light
        )
        val size = typedArray.indexCount
        (0 until size).asSequence()
            .map { typedArray.getIndex(it) }
            .forEach {
                when (it) {
                    R.styleable.Theme_lovevery_dark_light_force_dark ->
                        forceDark =
                            typedArray.getBoolean(it, false)

                    R.styleable.Theme_lovevery_dark_light_force_light ->
                        forceLight =
                            typedArray.getBoolean(it, false)
                }
            }
        typedArray.recycle()
        return DayNightContextParams(forceDark = forceDark, forceLight = forceLight)
    }

    fun getThemeWrappedContext(context: Context): Context =
        ContextThemeWrapper(context, R.style.Theme_Lovevery)
}