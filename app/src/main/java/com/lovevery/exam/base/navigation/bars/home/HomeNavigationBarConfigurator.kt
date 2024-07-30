package com.lovevery.exam.base.navigation.bars.home

import android.content.res.TypedArray
import androidx.constraintlayout.motion.widget.MotionLayout
import com.lovevery.exam.R
import com.lovevery.exam.base.navigation.NavigationBarUtils.removeViewAndNotify
import com.lovevery.exam.base.navigation.bars.base.NavigationBarConfigurator
import com.lovevery.exam.utils.extensions.getResourceId

internal class HomeNavigationBarConfigurator(private val interactor: HomeNavigationBar) :
    NavigationBarConfigurator() {

    override fun shouldEnableTransition() = false

    override fun getBottomView() = interactor.binding.textViewNavigationBarSubtitle

    override fun getStartMarginInsideToolbar() =
        R.dimen.navigation_bar_home_start_margin

    override fun getToolbarHeight() = R.dimen.navigation_bar_home_height

    override fun getBottomMargin() = R.dimen.spacing_2

    override fun clearElements(container: MotionLayout) = with(container) {
        jumpToState(R.id.start)
        interactor.binding.run {
            removeViewAndNotify(textViewNavigationBarTitle)
            removeViewAndNotify(textViewNavigationBarSubtitle)
            removeViewAndNotify(viewNavigationBarBackground)
        }
    }

    override fun configureAttrs(typedArray: TypedArray)  {
        with(typedArray) {
            getResourceId(
                R.styleable.NavigationBar_navigation_bar_title,
                interactor::setTitle
            )
            getResourceId(R.styleable.NavigationBar_navigation_bar_subtitle,
                interactor::setSubtitle
            )
            getResourceId(
                R.styleable.NavigationBar_navigation_bar_title_text_color,
                interactor::setTitleTextColor
            )
            getResourceId(
                R.styleable.NavigationBar_navigation_bar_subtitle_text_color,
                interactor::setSubtitleTextColor
            )
        }
    }
}