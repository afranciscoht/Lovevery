package com.lovevery.exam.base.navigation.bars.base

import android.content.res.TypedArray
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.Guideline
import com.lovevery.exam.R
import com.lovevery.exam.utils.extensions.updateHeight

internal abstract class NavigationBarConfigurator {

    abstract fun shouldEnableTransition(): Boolean

    abstract fun getBottomView(): View

    abstract fun clearElements(container: MotionLayout)

    open fun getStartMarginInsideToolbar() =
        R.dimen.navigation_bar_menu_margin

    open fun getEndMarginInsideToolbar() = R.dimen.navigation_bar_menu_margin

    open fun getToolbarHeight() = R.dimen.navigation_bar_default_size

    open fun getBottomMargin(): Int? = null

    open fun configureAttrs(typedArray: TypedArray) = Unit

    open fun configureComponent(container: MotionLayout) {
        configureLastElement(container)
        configureDimens(container)
        container.apply {
            if (isAttachedToWindow) {
                loadLayoutDescription(getMotionScene())
            }
            enableTransition(R.id.regular_transition, shouldEnableTransition())
        }
    }

    private fun configureLastElement(container: MotionLayout) {
        val barrier = container.findViewById<Barrier>(R.id.barrier_bottom)
        barrier.referencedIds = intArrayOf(getBottomView().id, R.id.toolbar)
    }

    private fun configureDimens(container: ViewGroup) {
        val resources = container.resources
        container.findViewById<Guideline>(R.id.guideline_start)?.setGuidelineBegin(
            resources.getDimensionPixelSize(getStartMarginInsideToolbar())
        )

        container.findViewById<Guideline>(R.id.guideline_end)?.setGuidelineEnd(
            resources.getDimensionPixelSize(getEndMarginInsideToolbar())
        )

        container.findViewById<Barrier>(R.id.barrier_bottom)?.margin =
            getBottomMargin()?.let(resources::getDimensionPixelSize) ?: 0

        container.findViewById<Toolbar>(R.id.toolbar)
            ?.updateHeight(resources.getDimensionPixelOffset(getToolbarHeight()))
    }

    private fun getMotionScene(): Int = R.xml.navigation_bar_scene
}