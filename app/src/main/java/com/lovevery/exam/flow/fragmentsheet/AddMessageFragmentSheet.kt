package com.lovevery.exam.flow.fragmentsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lovevery.exam.base.di.component.injector
import com.lovevery.exam.base.fragments.BaseBottomSheet
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.databinding.FragmentSheetAddMessageBinding
import com.lovevery.exam.flow.viewmodel.AddMessageViewModel
import com.lovevery.exam.utils.extensions.viewModel

class AddMessageFragmentSheet : BaseBottomSheet() {

    private val binding: FragmentSheetAddMessageBinding by viewBinding {
        FragmentSheetAddMessageBinding.inflate(layoutInflater)
    }

    private val viewModel: AddMessageViewModel by viewModel {
        requireActivity().injector.addMessageViewModel
    }

    override val isVisibleBackground = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        initChangeListener()
        buttonAddUser()
    }

    private fun buttonAddUser() {
        binding.apply {
            buttonAddMessage.setOnClickListener {
                this@AddMessageFragmentSheet.dismiss()
                // viewModel.addUser(editTextName.text.toString())
            }
        }
    }

    private fun bindViewModel() {
        // viewModel.getButtonAddUserEnable().observe(viewLifecycleOwner) {
        //     binding.buttonAddUser.isEnabled = it
        // }
    }

    private fun initChangeListener() {
        // binding.editTextName.addTextChangedListener {
        //     viewModel.setNewUserNameValue(it.toString().trim())
        // }
    }

    companion object {
        fun newInstance() = AddMessageFragmentSheet()
    }
}