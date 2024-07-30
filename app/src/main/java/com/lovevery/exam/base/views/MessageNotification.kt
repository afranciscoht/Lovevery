package com.lovevery.exam.base.views

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.lovevery.exam.R
import com.lovevery.exam.base.views.snack.Snackbar
import com.lovevery.exam.base.views.snack.SnackbarState

fun Activity.showMessage(
    state: SnackbarState = SnackbarState.SUCCESS,
    duration: Int,
    message: String,
    @DrawableRes endIcon: Int? = null
) {
    val snackbarView = Snackbar(applicationContext).apply {
        setText(message)
        setState(state)
        endIcon?.let(::setEndIcon)
        setPadding(resources.getDimensionPixelSize(R.dimen.spacing_5))
    }

    Toast(applicationContext).apply {
        setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
        this.duration = duration
        view = snackbarView
    }.show()
}

fun Activity.showMessage(
    state: SnackbarState = SnackbarState.SUCCESS,
    message: String,
    @DrawableRes endIcon: Int? = null
) {
    showMessage(state, Toast.LENGTH_LONG, message, endIcon)
}

fun Activity.showSuccessMessage(message: String) {
    showMessage(SnackbarState.SUCCESS, message)
}

fun Activity.showAlertMessage(message: String) {
    showMessage(SnackbarState.ALERT, message)
}

fun Activity.showErrorMessage(message: String) {
    showMessage(SnackbarState.ERROR, message)
}

fun Activity.showSuccessMessage(@StringRes messageRes: Int) {
    showSuccessMessage(getString(messageRes))
}

fun Activity.showAlertMessage(@StringRes messageRes: Int) {
    showAlertMessage(getString(messageRes))
}

fun Activity.showErrorMessage(@StringRes messageRes: Int) {
    showErrorMessage(getString(messageRes))
}

fun Fragment.showMessage(
    state: SnackbarState = SnackbarState.SUCCESS,
    duration: Int,
    message: String,
    @DrawableRes endIcon: Int? = null
) {
    requireActivity().showMessage(state, duration, message, endIcon)
}

fun Fragment.showMessage(
    state: SnackbarState = SnackbarState.SUCCESS,
    message: String,
    @DrawableRes endIcon: Int? = null
) {
    requireActivity().showMessage(state, message, endIcon)
}

fun Fragment.showSuccessMessage(message: String) {
    requireActivity().showSuccessMessage(message)
}

fun Fragment.showAlertMessage(message: String) {
    requireActivity().showAlertMessage(message)
}

fun Fragment.showErrorMessage(message: String) {
    requireActivity().showErrorMessage(message)
}

fun Fragment.showSuccessMessage(@StringRes messageRes: Int) {
    requireActivity().showSuccessMessage(messageRes)
}

fun Fragment.showAlertMessage(@StringRes messageRes: Int) {
    requireActivity().showAlertMessage(messageRes)
}

fun Fragment.showErrorMessage(@StringRes messageRes: Int) {
    requireActivity().showErrorMessage(messageRes)
}