package com.lovevery.exam.base.navigation.interfaces

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.lovevery.exam.base.navigation.NavigationBar
import java.lang.ref.WeakReference

open class NavigationBarOnDestinationChangedListener(
    navigationBar: NavigationBar
) : NavController.OnDestinationChangedListener {

    protected val navBarContainerLayout = WeakReference(navigationBar)

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        navBarContainerLayout.get()?.let {
            it.showNavigationButton()
            onDestinationChanged(it, controller, destination, arguments)
        }
    }

    open fun onDestinationChanged(
        navigationBar: NavigationBar,
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) = Unit
}
