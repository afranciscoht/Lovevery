package com.lovevery.exam.flow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.lovevery.exam.base.application.InjectableApplication_MembersInjector
import com.lovevery.exam.base.di.component.injector
import com.lovevery.exam.base.fragments.BaseBottomSheet
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.databinding.FragmentSheetAddUserBinding
import com.lovevery.exam.flow.viewmodel.MessagesViewModel
import com.lovevery.exam.utils.extensions.activityViewModel

class AddUserFragmentSheet : BaseBottomSheet() {

    private val binding: FragmentSheetAddUserBinding by viewBinding {
        FragmentSheetAddUserBinding.inflate(layoutInflater)
    }

    private val viewModel: MessagesViewModel by activityViewModel {
        requireActivity().injector.messagesViewModel
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
    }

    private fun bindViewModel() {
        viewModel.getButtonAddUserEnable().observe(viewLifecycleOwner) {
            binding.buttonAddUser.isEnabled = it
        }
    }

    private fun initChangeListener() {
        binding.editTextName.addTextChangedListener {
            viewModel.setNewUserNameValue(it.toString().trim())
        }
    }

    companion object {
        fun newInstance() = AddUserFragmentSheet()
    }
}