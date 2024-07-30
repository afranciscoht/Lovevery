package com.lovevery.exam.base.fragments

import androidx.fragment.app.Fragment
import com.lovevery.exam.base.activities.BaseFragmentActivity
import com.lovevery.exam.utils.extensions.className

abstract class FragmentView : Fragment() {

    open fun getName(): String = className()

    val baseActivity by lazy {
        requireActivity() as BaseFragmentActivity
    }

    open fun onBackPressed() = Unit
}