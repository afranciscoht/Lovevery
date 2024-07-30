package com.lovevery.exam.base.views.snack

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.lovevery.exam.R

enum class SnackbarState(
    @DrawableRes internal val startIcon: Int,
    @ColorRes internal val startIconColor: Int
) {
    SUCCESS(
        R.drawable.ic_filled_check,
        R.color.green_60
    ),
    ALERT(
        R.drawable.ic_outline_info,
        R.color.orange_50
    ),
    ERROR(
        R.drawable.ic_outline_warning,
        R.color.red_50
    )
}