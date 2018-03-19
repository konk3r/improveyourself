package com.improve.improveyourself.ui.view

import android.widget.AdapterView
import android.widget.ArrayAdapter

/**
 * Created by konk3r on 2/11/18.
 */
interface TabContainerView {
    fun setToolbarSpinnerAdapter(adapter: ArrayAdapter<String>)
    fun setToolbarSpinnerPosition(position: Int)
    fun setSpinnerSelectedListener(listener: AdapterView.OnItemSelectedListener)
}