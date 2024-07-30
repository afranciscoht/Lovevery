package com.lovevery.exam.base.navigation.bars.compact

import android.content.res.TypedArray
import androidx.constraintlayout.motion.widget.MotionLayout
import com.lovevery.exam.R
import com.lovevery.exam.base.navigation.NavigationBarUtils.removeViewAndNotify
import com.lovevery.exam.base.navigation.bars.base.NavigationBarConfigurator
import com.lovevery.exam.utils.extensions.getResourceId

internal class CompactNavigationBarConfigurator(
    private val interactor: CompactNavigationBar
) : NavigationBarConfigurator() {

    override fun shouldEnableTransition() = false

    override fun getBottomView() = interactor.binding.textViewNavigationBarTitle

    override fun clearElements(container: MotionLayout) = with(container) {
        jumpToState(R.id.start)
        removeViewAndNotify(interactor.binding.textViewNavigationBarTitle)
        removeViewAndNotify(interactor.binding.textViewNavigationBarSubtitle)
        removeViewAndNotify(interactor.binding.textViewNavigationBarEndText)
    }

    override fun configureAttrs(typedArray: TypedArray) {
        with(typedArray) {
            getResourceId(
                R.styleable.NavigationBar_navigation_bar_title,
                interactor::setTitle
            )
            getResourceId(
                R.styleable.NavigationBar_navigation_bar_title_text_color,
                interactor::setTitleTextColor
            )
        }
    }
}