package com.lovevery.exam.base.navigation.bars.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.lovevery.exam.base.navigation.bars.base.NavigationBarInteractor
import com.lovevery.exam.databinding.NavigationBarHomeContentBinding
import com.lovevery.exam.utils.extensions.setTextColorResource
import com.lovevery.exam.utils.extensions.show

class HomeNavigationBar(container: ViewGroup) : NavigationBarInteractor(container) {

    internal val binding = NavigationBarHomeContentBinding.inflate(
        LayoutInflater.from(container.context),
        container
    )

    fun setTitle(@StringRes resId: Int) {
        binding.textViewNavigationBarTitle.setText(resId)
    }

    fun setTitle(title: CharSequence) {
        binding.textViewNavigationBarTitle.text = title
    }

    fun setTitleTextColor(@ColorRes color: Int) {
        binding.textViewNavigationBarTitle.setTextColorResource(color)
    }

    fun setSubtitle(subtitle: CharSequence) {
        binding.textViewNavigationBarSubtitle.text = subtitle
    }

    fun setSubtitle(@StringRes resId: Int) {
        binding.textViewNavigationBarSubtitle.setText(resId)
    }

    fun setSubtitleTextColor(@ColorRes color: Int) {
        binding.textViewNavigationBarSubtitle.setTextColorResource(color)
    }

    override fun show(show: Boolean) {
        toolbar.get()?.show(show)
        binding.apply {
            viewNavigationBarBackground.show(show)
            textViewNavigationBarTitle.show(show)
            textViewNavigationBarSubtitle.show(show)
        }
    }
}