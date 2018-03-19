package com.improve.improveyourself.ui.view

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.improve.improveyourself.ui.controller.TabContainerController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_main.*

/**
 * Created by konk3r on 2/11/18.
 */
class TabContainerViewImpl(override val containerView: View, val controller: TabContainerController) :
        LayoutContainer, TabContainerView {

    override fun setToolbarSpinnerAdapter(adapter: ArrayAdapter<String>) {
        main_toolbar_spinner.adapter = adapter
    }

    override fun setToolbarSpinnerPosition(position: Int) {
        main_toolbar_spinner.setSelection(position)
    }

    override fun setSpinnerSelectedListener(listener: AdapterView.OnItemSelectedListener) {
        main_toolbar_spinner.onItemSelectedListener = listener
    }

}