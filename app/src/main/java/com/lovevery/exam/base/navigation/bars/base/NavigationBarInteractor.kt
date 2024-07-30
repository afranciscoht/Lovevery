package com.lovevery.exam.base.navigation.bars.base

import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.lovevery.exam.R
import java.lang.ref.WeakReference

abstract class NavigationBarInteractor(container: ViewGroup) {

    internal val toolbar = WeakReference(
        container.findViewById<Toolbar>(R.id.toolbar)
    )

    abstract fun show(show: Boolean)
}