package com.improve.improveyourself.ui.view

import android.view.View
import com.improve.improveyourself.ui.controller.TabContainerController
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by konk3r on 2/11/18.
 */
class TabContainerViewImpl(override val containerView: View, val controller: TabContainerController) :
        LayoutContainer, TabContainerView {

}