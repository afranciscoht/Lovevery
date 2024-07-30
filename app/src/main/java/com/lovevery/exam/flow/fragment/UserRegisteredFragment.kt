package com.lovevery.exam.flow.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovevery.exam.R
import com.lovevery.exam.base.di.component.injector
import com.lovevery.exam.base.fragments.FragmentView
import com.lovevery.exam.base.fragments.viewBinding
import com.lovevery.exam.base.navigation.NavigationBarProvider
import com.lovevery.exam.base.navigation.bars.compact.CompactNavigationBar
import com.lovevery.exam.databinding.FragmentUsersRegisteredBinding
import com.lovevery.exam.flow.action.MessagesAction
import com.lovevery.exam.flow.adapter.NewUserAdapter
import com.lovevery.exam.flow.fragmentsheet.AddUserFragmentSheet
import com.lovevery.exam.flow.viewmodel.MessagesViewModel
import com.lovevery.exam.utils.extensions.activityViewModel
import com.lovevery.exam.utils.extensions.className
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class UserRegisteredFragment : FragmentView() {

    private val viewModel: MessagesViewModel by activityViewModel {
        requireActivity().injector.messagesViewModel
    }

    private val binding: FragmentUsersRegisteredBinding by viewBinding {
        FragmentUsersRegisteredBinding.inflate(layoutInflater)
    }

    private lateinit var usersAdapter: GroupAdapter<GroupieViewHolder>

    private var navigationBarProvider: NavigationBarProvider? = null
    private var listener: UserRegisteredListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? UserRegisteredListener
        navigationBarProvider = context as? NavigationBarProvider
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
            setTitle(getString(R.string.home_message_home))
        }
        initButtonListener()
        bindViewModel()
        initRecyclerView()
    }

    private fun bindViewModel() {
        viewModel.getAction().observe(viewLifecycleOwner, ::handleAction)
    }

    private fun handleAction(action: MessagesAction) {
        if (action is MessagesAction.ShowUserList) {
            createItemList(action.listUsers)
        }
    }

    private fun createItemList(userList: List<String>) {
        usersAdapter.clear()
        usersAdapter.addAll(
            userList.map {
                NewUserAdapter(it) { name ->
                    listener?.navigateToDetailMessage(name)
                }
            }
        )
    }

    private fun initButtonListener() {
        binding.buttonAddUser.setOnClickListener {
            AddUserFragmentSheet.newInstance().show(childFragmentManager, className())
        }
    }

    private fun initRecyclerView() {
        usersAdapter = GroupAdapter()
        binding.recyclerViewUsers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = usersAdapter
        }
    }

    interface UserRegisteredListener {
        fun navigateToDetailMessage(userName: String)
    }
}