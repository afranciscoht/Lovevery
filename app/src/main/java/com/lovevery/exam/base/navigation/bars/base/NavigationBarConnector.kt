package com.lovevery.exam.base.navigation.bars.base

import android.content.res.TypedArray
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.lovevery.exam.base.navigation.NavigationBarMode
import com.lovevery.exam.base.navigation.bars.compact.CompactNavigationBar
import com.lovevery.exam.base.navigation.bars.home.HomeNavigationBar
import com.lovevery.exam.base.navigation.bars.compact.CompactNavigationBarConfigurator
import com.lovevery.exam.base.navigation.bars.home.HomeNavigationBarConfigurator

class NavigationBarConnector {

    var interactor: NavigationBarInteractor? = null

    private var configurator: NavigationBarConfigurator? = null

    private fun clear(container: MotionLayout) {
        configurator?.clearElements(container)
        configurator = null
        interactor = null
    }

    internal fun configureMode(
        container: MotionLayout,
        mode: NavigationBarMode,
        typedArray: TypedArray? = null
    ) {
        clear(container)

        when (mode) {
            NavigationBarMode.COMPACT -> buildCompactElements(container)
            NavigationBarMode.HOME -> buildHomeElements(container)
        }

        typedArray?.let { configurator?.configureAttrs(it) }
        configurator?.configureComponent(container)
    }

    private fun buildCompactElements(container: ViewGroup) {
        CompactNavigationBar(container).also {
            interactor = it
            configurator = CompactNavigationBarConfigurator(it)
        }
    }

    private fun buildHomeElements(container: ViewGroup) {
        HomeNavigationBar(container).also {
            interactor = it
            configurator = HomeNavigationBarConfigurator(it)
        }
    }

}