package com.lovevery.exam.flow.adapter

import android.view.View
import com.lovevery.exam.R
import com.lovevery.exam.databinding.ItemViewUserCreatedBinding
import com.xwray.groupie.viewbinding.BindableItem

class NewUserAdapter(
    private val userName: String,
    val onUserTapedListener: (String) -> Unit
) : BindableItem<ItemViewUserCreatedBinding>() {

    override fun bind(viewBinding: ItemViewUserCreatedBinding, position: Int) {
        viewBinding.apply {
            textViewName.text = userName
            root.setOnClickListener {
                onUserTapedListener(userName)
            }
        }
    }

    override fun getLayout() = R.layout.item_view_user_created

    override fun initializeViewBinding(view: View) = ItemViewUserCreatedBinding.bind(view)
}