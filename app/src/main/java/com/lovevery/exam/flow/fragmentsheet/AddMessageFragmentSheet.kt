package com.lovevery.exam.flow.fragmentsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.lovevery.exam.base.di.component.injector
import com.lovevery.exam.base.fragments.BaseBottomSheet
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.databinding.FragmentSheetAddMessageBinding
import com.lovevery.exam.flow.viewmodel.AddMessageViewModel
import com.lovevery.exam.utils.extensions.activityViewModel
import com.lovevery.exam.utils.extensions.show

class AddMessageFragmentSheet : BaseBottomSheet() {

    private val binding: FragmentSheetAddMessageBinding by viewBinding {
        FragmentSheetAddMessageBinding.inflate(layoutInflater)
    }

    private val viewModel: AddMessageViewModel by activityViewModel {
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
                viewModel.sendNewMessage(
                    editTextTitle.text.toString(), editTextBody.text.toString()
                )
            }
        }
    }

    private fun bindViewModel() {
        viewModel.getButtonEnable().observe(viewLifecycleOwner) {
            binding.buttonAddMessage.isEnabled = it
        }
        viewModel.getShowProgress().observe(viewLifecycleOwner) {
            binding.frameLayoutProgress.show(it)
            if (it.not()) {
                this@AddMessageFragmentSheet.dismiss()
            }
        }
    }

    private fun initChangeListener() {
        binding.editTextTitle.addTextChangedListener {
            viewModel.setSubjectValue(it.toString().trim())
        }
        binding.editTextBody.addTextChangedListener {
            viewModel.setBodyValue(it.toString().trim())
        }
    }

    companion object {
        fun newInstance() = AddMessageFragmentSheet()
    }
}