package com.lovevery.exam.base.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.updateLayoutParams
import com.lovevery.exam.R
import com.lovevery.exam.databinding.NavigationVarContainerBinding
import com.lovevery.exam.base.navigation.bars.base.NavigationBarConnector
import com.lovevery.exam.base.navigation.bars.base.NavigationBarInteractor
import com.lovevery.exam.utils.extensions.ViewUtils.createDayNightThemedContext
import com.lovevery.exam.utils.extensions.getColor
import com.lovevery.exam.utils.extensions.getColorStateList
import com.lovevery.exam.utils.extensions.getDrawable
import com.lovevery.exam.utils.extensions.getEnum
import com.lovevery.exam.utils.extensions.getResourceIdOrNull
import com.lovevery.exam.utils.extensions.show
import com.lovevery.exam.utils.extensions.showShimmerLayout

class NavigationBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MotionLayout(createDayNightThemedContext(context, attrs), attrs, defStyleAttr) {

    private val binding = NavigationVarContainerBinding.inflate(
        LayoutInflater.from(createDayNightThemedContext(context, attrs)), this
    )

    private val toolbarActionConfigurator = ToolbarActionConfigurator(binding.toolbar)
    private var onActionItemClicked: ((Int) -> Unit)? = null

    val connector = NavigationBarConnector()

    private var shouldShowNavigationButton = false

    init {
        initAttrs(attrs, defStyleAttr)
        configureNavigationButton()
    }

    private fun initAttrs(attributeSet: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.NavigationBar,
            defStyleAttr,
            R.style.Lovevery_NavigationBar
        )

        val navigationBarMode = typedArray.getEnum(
            R.styleable.NavigationBar_navigation_bar_mode,
            NavigationBarMode.COMPACT
        )
        connector.configureMode(this, navigationBarMode, typedArray)

        setBackgroundColorResource(
            typedArray.getResourceId(
                R.styleable.NavigationBar_navigation_bar_background,
                R.color.white
            )
        )

        shouldShowNavigationButton =
            typedArray.getBoolean(R.styleable.NavigationBar_navigation_bar_show_back_button, false)
        showNavigationButton()

        val firstMenuIcon = typedArray.getResourceIdOrNull(
            R.styleable.NavigationBar_navigation_bar_first_menu_icon
        )
        val secondMenuIcon = typedArray.getResourceIdOrNull(
            R.styleable.NavigationBar_navigation_bar_second_menu_icon
        )
        val shouldShowCircularMenuIcon = typedArray.getBoolean(
            R.styleable.NavigationBar_navigation_bar_circular_background_menu_icon, false
        )
        configureMenuActions(firstMenuIcon, secondMenuIcon, shouldShowCircularMenuIcon)

        typedArray.recycle()
    }

    private fun configureNavigationButton() {
        val contentDescription = "navigation_description"
        val resultViews = ArrayList<View>()
        binding.toolbar.apply {
            navigationContentDescription = contentDescription
            findViewsWithText(resultViews, contentDescription, FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
            navigationContentDescription = null
        }

        resultViews.firstOrNull()?.updateLayoutParams<Toolbar.LayoutParams> {
            marginStart = resources.getDimensionPixelSize(
                R.dimen.spacing_7
            )
            val size = resources.getDimensionPixelSize(R.dimen.spacing_9)
            height = size
            width = size
        }
    }

    internal fun configureActionBar(activity: AppCompatActivity) {
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun setNavigationOnClickListener(onClickListener: OnClickListener) {
        binding.toolbar.setNavigationOnClickListener(onClickListener)
    }

    fun showNavigationButton(showNavigationButton: Boolean = shouldShowNavigationButton) {
        shouldShowNavigationButton = showNavigationButton
        binding.toolbar.navigationIcon = if (showNavigationButton) {
            getDrawable(R.drawable.ic_back_button)
        } else {
            null
        }
    }

    fun setNavigationIcon(@DrawableRes customNavigationIcon: Int?) {
        binding.toolbar.navigationIcon = customNavigationIcon?.let { getDrawable(it) }
    }

    fun configureMenuActions(
        @DrawableRes firstIcon: Int? = null,
        @DrawableRes secondIcon: Int? = null,
        shouldShowCircularBackground: Boolean = false,
        @ColorRes firstIconColor: Int? = null,
        @ColorRes secondIconColor: Int? = null,
        onActionItemClicked: ((Int) -> Unit)? = null
    ) {
        this.onActionItemClicked = onActionItemClicked
        toolbarActionConfigurator.configureActions(
            firstIcon,
            secondIcon,
            firstIconColor,
            secondIconColor,
            shouldShowCircularBackground
        ) { actionId ->
            this.onActionItemClicked?.invoke(actionId)
        }
    }

    fun setActionContainer(view: View) {
        toolbarActionConfigurator.getActionContainer()?.addView(view)
    }

    fun removeActionContainer() {
        toolbarActionConfigurator.clearActions()
    }

    fun setBackgroundColorResource(@ColorRes backgroundColor: Int) {
        val color = getColor(backgroundColor)
        binding.toolbar.setBackgroundColor(color)
        setBackgroundColor(color)
    }

    fun showToolbar(show: Boolean = true) {
        binding.toolbar.show(show)
    }

    fun showNavigationBar(show: Boolean = true) {
        connector.interactor?.show(show)
    }

    fun setOnMenuActionItemClicked(onActionItemClicked: (Int) -> Unit) {
        this.onActionItemClicked = onActionItemClicked
    }

    fun hideMenuActions() = toolbarActionConfigurator.hideActions()

    fun showMenuActions() = toolbarActionConfigurator.showActions()

    fun showShimmer(
        show: Boolean,
        @ColorRes color: Int = R.color.neutral_20
    ) {
        binding.apply {
            shimmerView.backgroundTintList = getColorStateList(color)
            shimmerView.show(show)
            shimmerLayout.showShimmerLayout(show)
        }
    }

    inline fun <reified T : NavigationBarInteractor> configure(update: T.() -> Unit) {
        if (connector.interactor !is T) {
            val mode = NavigationBarMode.getType(T::class.java)
            configure(mode)
        }

        update(connector.interactor as T)
    }

    fun configure(navigationBarMode: NavigationBarMode) {
        connector.configureMode(this, navigationBarMode, typedArray = null)
    }
}