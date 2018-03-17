package com.improve.improveyourself.util

import android.animation.Animator
import android.view.View
import android.view.View.*
import com.improve.improveyourself.ui.animation.ImproveAnimationListener
import com.improve.improveyourself.util.ViewAnimationUtil.values.ALPHA_FULL
import com.improve.improveyourself.util.ViewAnimationUtil.values.ALPHA_GONE

fun View.fadeOut(startListener: (Animator) -> Unit = { animator -> {} },
                 endListener: (Animator) -> Unit = { animator -> {} }) {

    animate().alpha(ALPHA_GONE)
            .setDuration(animationMedium())
            .setListener(ImproveAnimationListener(startListener, endListener))
}

fun View.fadeIn(startListener: (Animator) -> Unit = { animator -> {} },
                endListener: (Animator) -> Unit = { animator -> {} }) {
    visibility = VISIBLE

    animate().alpha(ALPHA_FULL)
            .setDuration(animationMedium())
            .setListener(ImproveAnimationListener(startListener, endListener))
}

fun View.hide() {
    visibility = INVISIBLE
}

fun View.show() {
    visibility = VISIBLE
    alpha = ALPHA_FULL
}

fun View.setAsGone() {
    visibility = GONE
}

fun View.animationMedium(): Long {
    return context.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
}

class ViewAnimationUtil {
    companion object values {
        val ALPHA_FULL = 1f
        val ALPHA_GONE = 0f
    }
}
