package com.lovevery.exam.utils.extensions

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

/**
 * Objeto con atajos comunes para aplicar Constraints a un ConstraintSet
 */
object ConstraintUtils {

    fun ConstraintLayout.updateConstraints(
        constraintSet: ConstraintSet = ConstraintSet(),
        performUpdate: ConstraintSet.() -> Unit
    ) {
        constraintSet.clone(this)
        performUpdate(constraintSet)
        constraintSet.applyTo(this)
    }

    fun ConstraintSet.constraintSize(view: View, size: Int) {
        constrainWidth(view.id, size)
        constrainHeight(view.id, size)
    }

    fun ConstraintSet.constraintSizeToWrapContent(view: View) {
        constrainWidth(view.id, ConstraintSet.WRAP_CONTENT)
        constrainHeight(view.id, ConstraintSet.WRAP_CONTENT)
    }

    fun ConstraintSet.constraintSizeToMatchConstraint(view: View) {
        constrainWidth(view.id, ConstraintSet.MATCH_CONSTRAINT)
        constrainHeight(view.id, ConstraintSet.MATCH_CONSTRAINT)
    }

    fun ConstraintSet.constraintWidthToMatchConstraintHeightToWrapContent(view: View) {
        constrainWidth(view.id, ConstraintSet.MATCH_CONSTRAINT)
        constrainHeight(view.id, ConstraintSet.WRAP_CONTENT)
    }

    fun ConstraintSet.constraintHeightToMatchConstraintWidthToWrapContent(view: View) {
        constrainHeight(view.id, ConstraintSet.MATCH_CONSTRAINT)
        constrainWidth(view.id, ConstraintSet.WRAP_CONTENT)
    }

    fun ConstraintSet.constraintStartToStartOfParent(view: View, margin: Int = 0) {
        connect(view.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, margin)
    }

    fun ConstraintSet.constraintEndToEndOfParent(view: View, margin: Int = 0) {
        connect(view.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, margin)
    }

    fun ConstraintSet.constraintTopToTopOfParent(view: View, margin: Int = 0) {
        connect(view.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, margin)
    }

    fun ConstraintSet.constraintStartToEndOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.START, secondView.id, ConstraintSet.END, margin)
    }

    fun ConstraintSet.constraintStartToStartOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) = connect(firstView.id, ConstraintSet.START, secondView.id, ConstraintSet.START, margin)

    fun ConstraintSet.constraintEndToStartOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.END, secondView.id, ConstraintSet.START, margin)
    }

    fun ConstraintSet.constraintEndToEndOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.END, secondView.id, ConstraintSet.END, margin)
    }

    fun ConstraintSet.constraintTopToBottomOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.TOP, secondView.id, ConstraintSet.BOTTOM, margin)
    }

    fun ConstraintSet.constraintTopToTopOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.TOP, secondView.id, ConstraintSet.TOP, margin)
    }

    fun ConstraintSet.constraintBottomToTopOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.BOTTOM, secondView.id, ConstraintSet.TOP, margin)
    }

    fun ConstraintSet.constraintBottomToBottomOf(
        firstView: View,
        secondView: View,
        margin: Int = 0
    ) {
        connect(firstView.id, ConstraintSet.BOTTOM, secondView.id, ConstraintSet.BOTTOM, margin)
    }

    fun ConstraintSet.constraintBottomToBottomOfParent(view: View, margin: Int = 0) {
        connect(
            view.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM,
            margin
        )
    }

    fun ConstraintSet.constraintToParentOnAllSides(view: View, parentId: Int, margin: Int = 0) {
        connect(view.id, ConstraintSet.START, parentId, ConstraintSet.START, margin)
        connect(view.id, ConstraintSet.END, parentId, ConstraintSet.END, margin)
        connect(view.id, ConstraintSet.TOP, parentId, ConstraintSet.TOP, margin)
        connect(view.id, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM, margin)
    }
}
