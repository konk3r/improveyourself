package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.view.TomorrowsGoalsViewImpl

/**
 * Created by konk3r on 2/7/18.
 */

class TomorrowsGoalsController : Controller() {

    private lateinit var tomorrowsGoalsView: TomorrowsGoalsViewImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_tomorrows_goals, container, false)

        tomorrowsGoalsView = TomorrowsGoalsViewImpl(view, this)


        return view
    }

}
