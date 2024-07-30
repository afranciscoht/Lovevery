package com.lovevery.exam.flow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lovevery.exam.base.fragments.FragmentView
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.databinding.FragmentUsersRegisteredBinding
import com.lovevery.exam.utils.extensions.className

class UserRegisteredFragment : FragmentView() {

    private val binding: FragmentUsersRegisteredBinding by viewBinding {
        FragmentUsersRegisteredBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListener()
    }

    private fun initButtonListener() {
        binding.buttonAddUser.setOnClickListener {
            AddUserFragmentSheet.newInstance().show(childFragmentManager, className() )
        }
    }
}