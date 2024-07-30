package com.lovevery.exam.base.navigation

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.children
import com.google.android.material.imageview.ShapeableImageView
import com.lovevery.exam.R
import com.lovevery.exam.utils.extensions.getColor
import com.lovevery.exam.utils.extensions.getDrawable
import kotlin.reflect.KFunction2

object NavigationBarUtils {

    fun View.addCircularBackground(
        @ColorRes colorResource: Int = R.color.neutral_20
    ) {
        (getDrawable(R.drawable.bg_circle) as? GradientDrawable)?.let {
            it.setColor(getColor(colorResource))
            background = it
        }
    }

    internal fun ShapeableImageView.configureCircularIconSize() {
        val size = resources.getDimensionPixelOffset(
            R.dimen.icon_regular_size
        )
        layoutParams.height = size
        layoutParams.width = size

        val padding = resources.getDimensionPixelOffset(
            R.dimen.navigation_bar_circle_icon_padding
        )
        post { setContentPadding(padding, padding, padding, padding) }
    }

    fun Toolbar.findActionContainer() = children
        .filterIsInstance(LinearLayout::class.java)
        .firstOrNull()

    fun AppCompatActivity.setBackNavigationListener(navigationBar: NavigationBar) {
        navigationBar.setNavigationOnClickListener { onBackPressed() }
    }

    fun AppCompatActivity.configureActionBar(navigationBar: NavigationBar) {
        navigationBar.configureActionBar(this)
    }

    fun <T : ViewGroup.LayoutParams> createWrappedLayoutParams(
        layoutParam: KFunction2<Int, Int, T>,
        applyChanges: T.() -> Unit
    ) = layoutParam(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).also(applyChanges)

    fun MotionLayout.removeViewAndNotify(view: View) {
        removeView(view)
        onViewRemoved(view)
    }
}