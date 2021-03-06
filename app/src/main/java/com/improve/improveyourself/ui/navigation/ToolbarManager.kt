package com.improve.improveyourself.ui.navigation

import android.widget.AdapterView
import android.widget.ArrayAdapter

/**
 * Created by konk3r on 3/18/18.
 */
interface ToolbarManager {
    fun hideActionBar()
    fun displayActionBar()
    fun hideTitle()
    fun displayTitle()
    fun setTitle(text: String)
    fun setSpinnerPosition(position: Int)
    fun setSpinnerSelectedListener(listener: AdapterView.OnItemSelectedListener)
    fun setSpinnerAdapter(adapter: ArrayAdapter<String>)
    fun hideSpinner()
    fun displaySpinner()
}