package com.lovevery.exam.base.navigation.interfaces

import androidx.navigation.NavController
import com.lovevery.exam.base.navigation.NavigationBar

interface NavigationControllerProvider {
    fun provideNavController(): NavController
    fun provideOnDestinationChangedListener(
        navigationBar: NavigationBar
    ) = NavigationBarOnDestinationChangedListener(navigationBar)
}