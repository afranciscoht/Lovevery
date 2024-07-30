package com.lovevery.exam.base.views

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.lovevery.exam.R

const val ALERT_ERROR = 0
const val ALERT_V2 = 2
const val ALERT_SUCCESSFUL = 1
const val TOAST_OFFSET_MILLIS = 1000

object ToastUtils {

    private var lastShown: String = ""
    private var lastTimeShowed = System.currentTimeMillis()

    fun showSuccessfulAlert(activity: Activity, message: String, iconSuccessAlert: Int? = null) {
        (activity.window?.decorView?.rootView as? ViewGroup)?.let {
            showAlert(it.context, message, ALERT_SUCCESSFUL, iconSuccessAlert = iconSuccessAlert)
        }
    }

    private fun isRecent(message: String): Boolean = message == lastShown

    @Suppress("LongParameterList")
    @Synchronized
    private fun showAlert(
        context: Context,
        message: String,
        typeAlert: Int,
        contentDescription: String? = null,
        iconSuccessAlert: Int? = null,
        iconErrorAlert: Int? = null,
        iconStatus: Int? = null
    ) {
        val currentTimeMillis = System.currentTimeMillis()
        if (!isRecent(message) || currentTimeMillis - lastTimeShowed > TOAST_OFFSET_MILLIS) {
            showToast(
                context.applicationContext,
                createAlert(
                    context,
                    message,
                    typeAlert,
                    contentDescription,
                    iconSuccessAlert,
                    iconErrorAlert,
                    iconStatus
                )
            )
            lastShown = message
            lastTimeShowed = currentTimeMillis
        }
    }

    private fun showToast(context: Context, view: View) {
        Toast(context).apply {
            setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
            duration = Toast.LENGTH_LONG
            this.view = view
            show()
        }
    }

    private fun createAlert(
        context: Context,
        message: String,
        typeAlert: Int,
        contentDescription: String? = null,
        iconSuccessAlert: Int? = null,
        iconErrorAlert: Int? = null,
        iconStatus: Int? = null
    ): View {

        val view =
            LayoutInflater.from(context).inflate(R.layout.view_black_toast, null)
        contentDescription?.let {
            view.contentDescription = it
        }
        if (typeAlert == ALERT_V2) {
            val textViewTitle = view.findViewById<TextView>(R.id.textview_toast_title)
            val imageViewStatus = view.findViewById<ImageView>(R.id.imageView_toast_status_icon)
            textViewTitle.text = message
            imageViewStatus.setImageDrawable(
                iconStatus?.let {
                    ContextCompat.getDrawable(
                        context,
                        it
                    )
                }
            )
        } else {
            val textView = view.findViewById<TextView>(R.id.textView_toast)
            textView.text = message

            val iconResource = when (typeAlert) {
                ALERT_SUCCESSFUL -> iconSuccessAlert
                    ?: 0//R.drawable.pay_mod_common_ic_success_toast
                ALERT_ERROR -> iconErrorAlert ?: 0//R.drawable.pay_mod_common_ic_error_toast
                else -> null
            }
            val icon = view.findViewById<ImageView>(R.id.imageView_toast_icon)
            icon.setImageDrawable(iconResource?.let { ContextCompat.getDrawable(context, it) })
        }
        return view
    }
}