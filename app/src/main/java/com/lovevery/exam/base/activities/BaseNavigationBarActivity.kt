package com.lovevery.exam.base.activities

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.lovevery.exam.base.navigation.NavigationBarProvider
import com.lovevery.exam.base.navigation.NavigationBarUtils.configureActionBar
import com.lovevery.exam.base.navigation.NavigationBarUtils.setBackNavigationListener
import com.lovevery.exam.base.navigation.interfaces.NavigationControllerProvider

abstract class BaseNavigationBarActivity: BaseFragmentActivity(), NavigationBarProvider {

    override fun onStart() {
        super.onStart()
        setupActionBarWithNavBar()
    }

    private fun setupActionBarWithNavBar() {
        setBackNavigationListener(provideNavigationBar())

        if (this is NavigationControllerProvider) {
            configureActionBar(provideNavigationBar())
            NavigationUI.setupActionBarWithNavController(
                this,
                provideNavController(),
                AppBarConfiguration(emptySet())
            )
            provideNavController().addOnDestinationChangedListener(
                provideOnDestinationChangedListener(provideNavigationBar())
            )
            provideNavigationBar().showNavigationButton()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (this is NavigationControllerProvider) {
            provideNavController().navigateUp() || super.onSupportNavigateUp()
        } else {
            super.onSupportNavigateUp()
        }
    }

    override fun onBackPressed() {
        if (this is NavigationControllerProvider && handleCustomBackDispatcher().not()) {
            onSupportNavigateUp()
        } else {
            super.onBackPressed()
        }
    }

    open fun handleCustomBackDispatcher() = false
}