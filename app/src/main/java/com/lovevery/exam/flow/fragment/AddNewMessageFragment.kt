package com.lovevery.exam.flow.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.lovevery.exam.base.di.component.injector
import com.lovevery.exam.base.fragments.FragmentView
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.base.navigation.NavigationBarProvider
import com.lovevery.exam.base.navigation.bars.compact.CompactNavigationBar
import com.lovevery.exam.databinding.FragmentMessagesByUserSelectedBinding
import com.lovevery.exam.flow.fragmentsheet.AddMessageFragmentSheet
import com.lovevery.exam.flow.viewmodel.AddMessageViewModel
import com.lovevery.exam.utils.extensions.activityViewModel
import com.lovevery.exam.utils.extensions.className
import com.lovevery.exam.utils.extensions.show
import com.lovevery.exam.utils.extensions.viewModel

class AddNewMessageFragment : FragmentView() {

    private val binding: FragmentMessagesByUserSelectedBinding by viewBinding {
        FragmentMessagesByUserSelectedBinding.inflate(layoutInflater)
    }

    private var navigationBarProvider: NavigationBarProvider? = null

    private val args: AddNewMessageFragmentArgs by navArgs()

    private val viewModel: AddMessageViewModel by activityViewModel {
        requireActivity().injector.addMessageViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationBarProvider = context as? NavigationBarProvider
    }

    override fun onDetach() {
        super.onDetach()
        navigationBarProvider = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationBarProvider?.provideNavigationBar()?.configure<CompactNavigationBar> {
            setTitle(args.userName)
        }
        initButtonListener()
        bindViewModel()
        viewModel.setUserNameValue(args.userName)
    }

    private fun initButtonListener() {
        binding.buttonAddMessage.setOnClickListener {
            AddMessageFragmentSheet.newInstance().show(childFragmentManager, className())
        }
    }

    private fun bindViewModel() {
        // viewModel.getShowProgress().observe(viewLifecycleOwner) {
        //     binding.frameLayoutProgress.show(it)
        // }

    }
}