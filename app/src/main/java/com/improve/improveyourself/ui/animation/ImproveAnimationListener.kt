package com.improve.improveyourself.ui.animation

import android.animation.Animator

/**
 * Created by konk3r on 3/17/18.
 */
class ImproveAnimationListener(val startListener: (Animator) -> Unit, val endListener: (Animator) -> Unit) :
        Animator.AnimatorListener {

    override fun onAnimationStart(animator: Animator?) {
        if (animator != null) {
            startListener.invoke(animator)
        }
    }

    override fun onAnimationEnd(animator: Animator?) {
        if (animator != null) {
            endListener.invoke(animator)
        }
    }

    override fun onAnimationRepeat(animator: Animator?) {
    }

    override fun onAnimationCancel(animator: Animator?) {
    }
}