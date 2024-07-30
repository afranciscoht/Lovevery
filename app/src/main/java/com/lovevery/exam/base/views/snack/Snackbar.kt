package com.lovevery.exam.base.views.snack

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lovevery.exam.R
import com.lovevery.exam.databinding.SnackbarBinding
import com.lovevery.exam.utils.extensions.ViewUtils.createDayNightThemedContext
import com.lovevery.exam.utils.extensions.getEnum
import com.lovevery.exam.utils.extensions.getResourceId
import com.lovevery.exam.utils.extensions.getStateListOfColor
import com.lovevery.exam.utils.extensions.setImageAndVisibility
import com.lovevery.exam.utils.extensions.setTextResourceIfNotZero

class Snackbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(createDayNightThemedContext(context, attrs), attrs, defStyleAttr) {

    private val binding = SnackbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var onEndIconPressed: (() -> Unit)? = null

    init {
        initAttrs(attrs, defStyleAttr)
        binding.imageViewEndIcon.setOnClickListener { onEndIconPressed?.invoke() }
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.Snackbar,
            defStyleAttr,
            R.style.Lovevery_StoreCard
        )
        typedArray.apply {
            setState(getEnum(R.styleable.Snackbar_snackbar_state, SnackbarState.SUCCESS))
            getResourceId(R.styleable.Snackbar_android_text, ::setText)
            getResourceId(R.styleable.Snackbar_snackbar_end_icon, ::setEndIcon)
        }
    }

    fun setOnIconPressed(onIconPressed: (() -> Unit)) {
        onEndIconPressed = onIconPressed
    }

    fun setState(state: SnackbarState) {
        binding.imageViewStartIcon.apply {
            setImageResource(state.startIcon)
            imageTintList = getStateListOfColor(state.startIconColor)
        }
    }

    fun setText(text: String) {
        binding.textViewSnackbarLabel.text = text
    }

    fun setText(@StringRes textId: Int) {
        binding.textViewSnackbarLabel.setTextResourceIfNotZero(textId)
    }

    fun setEndIcon(icon: Drawable?) {
        binding.imageViewEndIcon.setImageAndVisibility(icon)
    }

    fun setEndIcon(@DrawableRes iconId: Int) {
        binding.imageViewEndIcon.setImageAndVisibility(iconId)
    }
}