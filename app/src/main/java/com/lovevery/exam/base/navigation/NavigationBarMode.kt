package com.lovevery.exam.base.navigation

import com.lovevery.exam.base.navigation.bars.base.NavigationBarInteractor
import com.lovevery.exam.base.navigation.bars.compact.CompactNavigationBar
import com.lovevery.exam.base.navigation.bars.home.HomeNavigationBar

enum class NavigationBarMode(private val classType: Class<out NavigationBarInteractor>) {
    COMPACT(CompactNavigationBar::class.java),
    HOME(HomeNavigationBar::class.java);

    companion object {
        fun getType(classType: Class<out NavigationBarInteractor>) = when (classType) {
            COMPACT.classType -> COMPACT
            HOME.classType -> HOME
            else -> throw IllegalArgumentException("Unsupported class type")
        }
    }
}