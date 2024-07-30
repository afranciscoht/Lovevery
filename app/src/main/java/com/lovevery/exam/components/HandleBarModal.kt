package com.lovevery.exam.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textview.MaterialTextView
import com.lovevery.exam.R
import com.lovevery.exam.databinding.ModalBinding
import com.lovevery.exam.utils.extensions.ConstraintUtils.constraintBottomToBottomOfParent
import com.lovevery.exam.utils.extensions.ConstraintUtils.constraintEndToEndOfParent
import com.lovevery.exam.utils.extensions.ConstraintUtils.constraintStartToStartOfParent
import com.lovevery.exam.utils.extensions.ConstraintUtils.constraintTopToBottomOf
import com.lovevery.exam.utils.extensions.ConstraintUtils.updateConstraints
import com.lovevery.exam.utils.extensions.TextStyle
import com.lovevery.exam.utils.extensions.ViewUtils.createDayNightThemedContext
import com.lovevery.exam.utils.extensions.getBoolean
import com.lovevery.exam.utils.extensions.getResourceId
import com.lovevery.exam.utils.extensions.setTextAndVisibility
import com.lovevery.exam.utils.extensions.setTextAppearance
import com.lovevery.exam.utils.extensions.setTextColorResource
import com.lovevery.exam.utils.extensions.setTextResourceIfNotZero
import com.lovevery.exam.utils.extensions.show
import com.lovevery.exam.utils.extensions.showOrDisappear

const val EMPTY_DIMEN_SPACE = 0

/**
 * Un bottom sheet modal que se expande para mostrar su contenido
 */
class HandleBarModal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(createDayNightThemedContext(context, attrs), attrs, defStyleAttr) {

    private var subtitleResource: Int = 0
    private var iconResource: Int = 0
    private var contentLayoutResource: Int = 0
    private var skeletonLayoutResource: Int = NO_ID
    private var peekHeight: Int = R.dimen.modal_peek_height
    private var shouldShowFloating: Boolean = false
    private var shouldShowFullScreen: Boolean = false

    private val textViewTitle: MaterialTextView
    private val textViewSubtitle: MaterialTextView
    private val imageViewIcon: ImageView
    private var contentView: View? = null
    private val viewContentBackground: View
    private val viewTopIndicator: View

    private val binding: ModalBinding =
        ModalBinding.inflate(LayoutInflater.from(context), this).also {
            viewTopIndicator = it.viewTopIndicator
            textViewTitle = it.textViewTitle
            textViewSubtitle = it.textViewSubtitle
            imageViewIcon = it.imageViewIcon
            viewContentBackground = it.viewContentBackground
        }

    init {
        initAttrs(attrs, defStyleAttr)
        init()
    }

    private fun initAttrs(attributeSet: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.HandleBarModal,
            defStyleAttr,
            R.style.Lovevery_HandleBarModal
        )

        with(typedArray) {
            getResourceId(R.styleable.HandleBarModal_handle_bar_modal_title, ::setTitleTextResource)
            subtitleResource = getResourceId(R.styleable.HandleBarModal_handle_bar_modal_subtitle)
            iconResource = getResourceId(R.styleable.HandleBarModal_handle_bar_modal_icon)
            contentLayoutResource = getResourceId(
                R.styleable.HandleBarModal_handle_bar_modal_content_layout
            )
            peekHeight = getResourceId(
                R.styleable.HandleBarModal_handle_bar_modal_peek_height, peekHeight
            )
            shouldShowFloating = getBoolean(R.styleable.HandleBarModal_handle_bar_modal_floating)
            skeletonLayoutResource =
                getResourceId(R.styleable.HandleBarModal_handle_bar_modal_skeleton_layout, NO_ID)
            shouldShowFullScreen =
                getBoolean(R.styleable.HandleBarModal_handle_bar_modal_full_screen)

            recycle()
        }
    }

    private fun init() {
        binding.textViewSubtitle.setTextResourceIfNotZero(subtitleResource)
        binding.imageViewIcon.setImageResource(iconResource)
        if (contentLayoutResource != 0) setContentLayout()
        configureFloatingModeIfRequired()
    }

    private fun configureFloatingModeIfRequired() {
        if (shouldShowFloating) {
            val horizontalMargin =
                resources.getDimensionPixelSize(R.dimen.spacing_3)
            updatePadding(
                left = horizontalMargin,
                right = horizontalMargin,
                bottom = resources.getDimensionPixelSize(R.dimen.spacing_5)
            )
            binding.viewContentBackground.setBackgroundResource(
                R.drawable.bg_floating_modal
            )
        }
    }

    private fun setContentLayout() {
        if (contentView != null) removeView(contentView)
        contentView = LayoutInflater.from(context).inflate(contentLayoutResource, null).apply {
            id = View.generateViewId()
        }
        addContentView()
    }

    private fun addContentView() {
        addView(contentView)
        setupContentConstraints()
    }

    /**
     * Agrega las constraints al contenido una vez agregado
     */
    private fun setupContentConstraints() {
        contentView?.let {
            val bottomMargin = if (shouldShowFullScreen && shouldShowFloating) {
                resources.getDimensionPixelSize(R.dimen.spacing_4)
            } else {
                EMPTY_DIMEN_SPACE
            }
            val containerHeight = if (shouldShowFullScreen) {
                LayoutParams.MATCH_CONSTRAINT_SPREAD
            } else {
                LayoutParams.WRAP_CONTENT
            }
            updateConstraints() {
                constraintTopToBottomOf(it, binding.viewDivider)
                constraintBottomToBottomOfParent(it, bottomMargin)
                constraintStartToStartOfParent(it)
                constraintEndToEndOfParent(it)
                constrainHeight(it.id, containerHeight)
                constrainWidth(it.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
                if (shouldShowFullScreen.not()) {
                    createVerticalChain(
                        binding.textViewSubtitle.id,
                        ConstraintSet.BOTTOM,
                        it.id,
                        ConstraintSet.BOTTOM,
                        intArrayOf(
                            binding.viewDivider.id,
                            it.id
                        ),
                        null,
                        ConstraintSet.CHAIN_PACKED
                    )
                }
            }
        }
    }

    /**
     * Una vez que se ha agregado la vista a la ventana se configura para funcionar con el CoordinatorLayout padre,
     * el modal solo funcionara adecuadamente si esta contenido dentro de un CoordinatorLayout
     */
    private fun setupInCoordinatorLayout() {
        if (parent is CoordinatorLayout) {
            (layoutParams as CoordinatorLayout.LayoutParams).also {
                it.behavior = BottomSheetBehavior<ConstraintLayout>()
            }
            setPeekHeight(peekHeight)
        } else {
            setupNormalConstraint()
        }
    }

    private fun setupNormalConstraint() {
        updateConstraints {
            constraintBottomToBottomOfParent(binding.viewContentBackground)
            constraintStartToStartOfParent(binding.viewContentBackground)
            constraintEndToEndOfParent(binding.viewContentBackground)
            constraintTopToBottomOf(
                binding.viewContentBackground,
                binding.viewTopIndicator,
                resources.getDimensionPixelSize(R.dimen.modal_content_margin)
            )
        }
    }

    /**
     * Una vez que se ha agregado la vista a la ventana se configura para funcionar con el CoordinatorLayout padre
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupInCoordinatorLayout()
    }

    /**
     * Esta funcion modifica el alto de la porcion que se muestra cuando el modal esta colapsado
     *
     * @param height el alto de la parte que se muestra
     */
    fun setPeekHeight(@DimenRes height: Int) {
        BottomSheetBehavior.from(this).also {
            it.peekHeight = resources.getDimensionPixelSize(height)
        }
    }

    /**
     * Asigna el texto del titulo, que es la TextView superior
     *
     * @param text El texto que se pondra en el titulo
     */
    fun setTitleText(text: CharSequence?) {
        textViewTitle.setTextAndVisibility(text)
    }

    /**
     * Asigna el texto del subtitulo, que es la TextView inferior
     *
     * @param text El texto que se pondra en el subtitulo
     */
    fun setSubtitleText(text: CharSequence) {
        textViewSubtitle.text = text
    }

    /**
     * Asigna un recurso de texto al titulo, que es la TextView superior
     *
     * @param titleResource El id del recurso de texto que se pondra en el titulo
     */
    fun setTitleTextResource(@StringRes titleResource: Int) {
        textViewTitle.setTextAndVisibility(titleResource)
    }

    /**
     * Asigna un recurso de texto al subtitulo, que es la TextView inferior
     *
     * @param titleResource El id del recurso de texto que se pondra en el subtitulo
     */
    fun setSubtitleTextResource(@StringRes titleResource: Int) {
        textViewSubtitle.setTextResourceIfNotZero(titleResource)
    }

    /**
     * Asigna un recurso de drawable al icono
     *
     * @param drawableResource El id del recurso drawable que se pondra en la ImageView
     */
    fun setIconResource(@DrawableRes drawableResource: Int) {
        binding.imageViewIcon.setImageResource(drawableResource)
    }

    /**
     * Muestra u oculta el divider
     *
     */
    fun showDivider(show: Boolean) {
        binding.viewDivider.show(show)
    }

    /**
     * Muestra u oculta todas las vistas que comprenden el encabezado del componente
     */
    fun configureHeaderVisibility(
        showTitle: Boolean = textViewTitle.isVisible,
        showSubtitle: Boolean = textViewSubtitle.isVisible,
        showIcon: Boolean = imageViewIcon.isVisible
    ) {
        textViewTitle.show(showTitle)
        textViewSubtitle.show(showSubtitle)
        imageViewIcon.show(showIcon)
    }

    fun showOrDisappearHeader(
        showTitle: Boolean? = null,
        showSubtitle: Boolean? = null,
        showIcon: Boolean? = null
    ) {
        showTitle?.let(textViewTitle::showOrDisappear)
        showSubtitle?.let(textViewSubtitle::showOrDisappear)
        showIcon?.let(imageViewIcon::showOrDisappear)
    }

    fun setTitleTextAppearance(textStyle: TextStyle) = textViewTitle.setTextAppearance(textStyle)

    fun setSubtitleTextAppearance(textStyle: TextStyle) =
        textViewSubtitle.setTextAppearance(textStyle)

    fun setTitleTextColor(@ColorRes colorId: Int) = textViewTitle.setTextColorResource(colorId)

    fun setSubtitleTextColor(@ColorRes colorId: Int) =
        textViewSubtitle.setTextColorResource(colorId)

    fun setOnIconClickListener(onClickListener: OnClickListener) {
        imageViewIcon.setOnClickListener(onClickListener)
    }

    fun setIconBackground(@DrawableRes backgroundId: Int) {
        imageViewIcon.setBackgroundResource(backgroundId)
    }

    fun updateSubtitleMargins(
        @Px left: Int? = null,
        @Px top: Int? = null,
        @Px right: Int? = null,
        @Px bottom: Int? = null
    ) {
        textViewSubtitle.updateLayoutParams<LayoutParams> {
            setMargins(
                left ?: leftMargin,
                top ?: topMargin,
                right ?: rightMargin,
                bottom ?: bottomMargin
            )
        }
    }

    /**
     * Asigna un recurso de layout al contenido del modal
     *
     * @param layoutRes el id del recurso de layout que si inflara dentro del contenido del modal. El modal crecera
     * al tamaño disponible, si el contenido es mas grande este layout deberia ser un ScrollView
     */
    fun setContentLayoutResource(@LayoutRes layoutRes: Int) {
        if (contentView != null) removeView(contentView)
        contentLayoutResource = layoutRes
        contentView = LayoutInflater.from(context).inflate(layoutRes, null).apply {
            id = View.generateViewId()
        }
        addContentView()
    }

    /**
     * Asigna una vista al contenido del modal
     *
     * @param layout la vista que se despliegara dentro del contenido del modal. El modal crecera al tamaño
     * disponible, si el contenido es mas grande esta vista deberia ser un ScrollView
     */
    fun setContentLayout(layout: View) {
        if (contentView != null) removeView(contentView)
        contentView = layout.apply { id = View.generateViewId() }
        addContentView()
    }

    fun showSkeleton(showSkeleton: Boolean = true) {
        if (skeletonLayoutResource == NO_ID) return

        if (binding.shimmerLayout.childCount == 0) {
            val viewLayout = LayoutInflater.from(context).inflate(skeletonLayoutResource, null)
            binding.shimmerLayout.addView(viewLayout)
        }

        binding.layoutSkeleton.show(showSkeleton)
        binding.shimmerLayout.show(showSkeleton)
    }

    fun showSkeleton(@LayoutRes skeletonLayout: Int) {
        skeletonLayoutResource = skeletonLayout
        binding.shimmerLayout.removeAllViews()
        showSkeleton()
    }

    fun showTopIndicator(showIndicator: Boolean) {
        viewTopIndicator.show(showIndicator)
    }
}
