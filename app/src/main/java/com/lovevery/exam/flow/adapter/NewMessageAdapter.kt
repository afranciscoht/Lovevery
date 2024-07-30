package com.lovevery.exam.flow.adapter

import android.view.View
import com.lovevery.exam.R
import com.lovevery.exam.databinding.ItemViewMessageBinding
import com.lovevery.exam.databinding.ItemViewUserCreatedBinding
import com.lovevery.exam.flow.model.MessageByUserUi
import com.xwray.groupie.viewbinding.BindableItem

class NewMessageAdapter(
    private val message: MessageByUserUi.BodyMessage,
) : BindableItem<ItemViewMessageBinding>() {

    override fun bind(viewBinding: ItemViewMessageBinding, position: Int) {
        viewBinding.apply {
            textViewSubject.text = message.subject
            textViewBody.text = message.message
        }
    }

    override fun getLayout() = R.layout.item_view_message

    override fun initializeViewBinding(view: View) = ItemViewMessageBinding.bind(view)
}