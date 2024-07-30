package com.lovevery.exam.base.navigation.bars.compact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.lovevery.exam.base.navigation.bars.base.NavigationBarInteractor
import com.lovevery.exam.databinding.NavigationBarCompactContentBinding
import com.lovevery.exam.utils.extensions.setTextColorResource
import com.lovevery.exam.utils.extensions.show

class CompactNavigationBar(container: ViewGroup) : NavigationBarInteractor(container) {

    internal val binding = NavigationBarCompactContentBinding.inflate(
        LayoutInflater.from(container.context),
        container
    )

    fun setTitle(@StringRes resId: Int) {
        binding.textViewNavigationBarTitle.setText(resId)
    }

    fun setTitle(title: CharSequence?) {
        binding.textViewNavigationBarTitle.text = title
    }

    fun setTitleTextColor(@ColorRes color: Int) {
        binding.textViewNavigationBarTitle.setTextColorResource(color)
    }

    fun setTitleTextStyle(@StyleRes style: Int) {
        TextViewCompat.setTextAppearance(binding.textViewNavigationBarTitle, style)
    }

    fun showSubtitle(show: Boolean) {
        binding.textViewNavigationBarSubtitle.show(show)
    }

    fun setSubtitle(@StringRes resId: Int) {
        binding.textViewNavigationBarSubtitle.setText(resId)
    }

    fun setSubtitle(subtitle: CharSequence?) {
        binding.textViewNavigationBarSubtitle.text = subtitle
    }

    fun setSubtitleTextColor(@ColorRes color: Int) {
        binding.textViewNavigationBarSubtitle.setTextColorResource(color)
    }

    fun setEndText(endText: CharSequence) {
        binding.textViewNavigationBarEndText.text = endText
    }

    fun sedEndTextColor(@ColorRes color: Int) {
        binding.textViewNavigationBarEndText.setTextColorResource(color)
    }

    fun showEndText(show: Boolean) {
        binding.textViewNavigationBarEndText.show(show)
    }

    override fun show(show: Boolean) {
        toolbar.get()?.show(show)
        binding.textViewNavigationBarTitle.show(show)
    }
}