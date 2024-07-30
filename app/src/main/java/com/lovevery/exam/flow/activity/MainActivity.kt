package com.lovevery.exam.flow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.lovevery.exam.R
import com.lovevery.exam.base.activities.BaseNavigationBarActivity
import com.lovevery.exam.base.navigation.NavigationBar
import com.lovevery.exam.base.navigation.interfaces.NavigationControllerProvider
import com.lovevery.exam.databinding.ActivityMainBinding
import com.lovevery.exam.flow.fragment.UserRegisteredFragment

class MainActivity :
    BaseNavigationBarActivity(),
    NavigationControllerProvider,
    UserRegisteredFragment.UserRegisteredListener {

    override fun provideNavigationBar() = binding.navigationBarMain

    override fun provideNavController() = findNavController(R.id.navHostFragment)

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun navigateToDetailMessage() {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        return when (provideNavController().currentDestination?.id) {
            R.id.home_messages -> {
                finish()
                true
            }
            else -> provideNavController().navigateUp()
        }
    }
}