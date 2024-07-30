package com.lovevery.exam.base.navigation

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.core.view.isVisible
import com.lovevery.exam.R
import com.lovevery.exam.base.navigation.NavigationBarUtils.addCircularBackground
import com.lovevery.exam.base.navigation.NavigationBarUtils.configureCircularIconSize
import com.lovevery.exam.base.navigation.NavigationBarUtils.createWrappedLayoutParams
import com.lovevery.exam.base.navigation.NavigationBarUtils.findActionContainer
import com.lovevery.exam.databinding.ToolbarMenuItemBinding
import com.lovevery.exam.utils.extensions.setTintColorResource
import java.lang.ref.WeakReference

typealias ToolbarParams = Toolbar.LayoutParams
typealias LinearLayoutParams = LinearLayout.LayoutParams

internal class ToolbarActionConfigurator(toolbar: Toolbar) {

    private val toolbar = WeakReference(toolbar)

    init {
        toolbar.addView(buildActionContainer(toolbar.context))
    }

    fun configureActions(
        @DrawableRes firstIcon: Int? = null,
        @DrawableRes secondIcon: Int? = null,
        @ColorRes firstIconColor: Int? = null,
        @ColorRes secondIconColor: Int? = null,
        isCircularBackground: Boolean,
        onActionSelected: (Int) -> Unit
    ) {
        clearActions()
        toolbar.get()?.run {
            val firstAction = firstIcon?.let {
                buildAction(
                    context,
                    R.id.first_menu_action,
                    it,
                    firstIconColor,
                    isCircularBackground,
                    onActionSelected,
                )
            }

            val secondAction = secondIcon?.let {
                buildAction(
                    context,
                    R.id.second_menu_action,
                    it,
                    secondIconColor,
                    isCircularBackground,
                    onActionSelected,
                )
            }

            val actionContainer = getActionContainer()

            firstAction?.let {
                actionContainer?.addView(it.root)
            }
            secondAction?.let {
                if (firstAction == null) {
                    actionContainer?.addView(it.root)
                } else {
                    actionContainer?.addView(it.root, buildActionLayoutParams(context))
                }
            }
        }
    }

    fun clearActions() = getActionContainer()?.removeAllViews()

    fun showActions() = getActionContainer()?.forEach { it.isVisible = true }

    fun hideActions() = getActionContainer()?.forEach { it.isVisible = false }

    private fun buildAction(
        context: Context,
        actionId: Int,
        iconId: Int,
        iconColor: Int?,
        isCircularBackground: Boolean,
        onActionSelected: (Int) -> Unit
    ) = ToolbarMenuItemBinding.inflate(LayoutInflater.from(context)).apply {
        imageViewMenuItem.apply {
            id = actionId
            setImageResource(iconId)
            iconColor?.let(::setTintColorResource)
            if (isCircularBackground) {
                addCircularBackground()
                configureCircularIconSize()
            }
        }

        root.setOnClickListener { onActionSelected(actionId) }
    }

    fun getActionContainer() = toolbar.get()?.findActionContainer()

    private fun buildActionContainer(context: Context) = LinearLayout(context).apply {
        layoutParams = createWrappedLayoutParams(::ToolbarParams) {
            gravity = Gravity.END
            marginEnd = resources.getDimensionPixelSize(R.dimen.spacing_7)
            clipChildren = false
            clipToPadding = false
        }
    }

    private fun buildActionLayoutParams(context: Context) =
        createWrappedLayoutParams(::LinearLayoutParams) {
            marginStart = context.resources.getDimensionPixelSize(
                R.dimen.spacing_5
            )
        }
}