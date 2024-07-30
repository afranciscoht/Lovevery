package com.lovevery.exam.flow.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovevery.exam.base.di.component.injector
import com.lovevery.exam.base.fragments.FragmentView
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.base.navigation.NavigationBarProvider
import com.lovevery.exam.base.navigation.bars.compact.CompactNavigationBar
import com.lovevery.exam.base.views.showMessage
import com.lovevery.exam.base.views.snack.SnackbarState
import com.lovevery.exam.databinding.FragmentMessagesByUserSelectedBinding
import com.lovevery.exam.flow.action.SendMessageAction
import com.lovevery.exam.flow.adapter.NewMessageAdapter
import com.lovevery.exam.flow.fragmentsheet.AddMessageFragmentSheet
import com.lovevery.exam.flow.model.MessageByUserUi
import com.lovevery.exam.flow.viewmodel.AddMessageViewModel
import com.lovevery.exam.utils.extensions.activityViewModel
import com.lovevery.exam.utils.extensions.className
import com.lovevery.exam.utils.extensions.hide
import com.lovevery.exam.utils.extensions.show
import com.lovevery.exam.utils.extensions.viewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class AddNewMessageFragment : FragmentView() {

    private val binding: FragmentMessagesByUserSelectedBinding by viewBinding {
        FragmentMessagesByUserSelectedBinding.inflate(layoutInflater)
    }

    private var navigationBarProvider: NavigationBarProvider? = null

    private val args: AddNewMessageFragmentArgs by navArgs()

    private val viewModel: AddMessageViewModel by activityViewModel {
        requireActivity().injector.addMessageViewModel
    }

    private lateinit var listMessageAdapter: GroupAdapter<GroupieViewHolder>

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
        initRecyclerView()
        viewModel.setUserNameValue(args.userName)
    }

    private fun initButtonListener() {
        binding.buttonAddMessage.setOnClickListener {
            AddMessageFragmentSheet.newInstance().show(childFragmentManager, className())
        }
    }

    private fun bindViewModel() {
        viewModel.getAction().observe(viewLifecycleOwner, ::handleAction)
    }

    private fun handleAction(action: SendMessageAction) {
        when(action) {
            is SendMessageAction.ShowLoading -> binding.frameLayoutProgress.show(action.showLoading)
            is SendMessageAction.ShowListMessageByUser -> createItemList(action.listMessage)
            is SendMessageAction.ShowMessage -> requireActivity().showMessage(
                SnackbarState.ERROR, action.message
            )
        }
    }

    private fun createItemList(listMessage: List<MessageByUserUi.BodyMessage>) {
        binding.lottieAnimationLayer.hide()
        listMessageAdapter.clear()
        listMessageAdapter.addAll(
            listMessage.map {
                NewMessageAdapter(it)
            }
        )
    }

    private fun initRecyclerView() {
        listMessageAdapter = GroupAdapter()
        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listMessageAdapter
        }
    }
}