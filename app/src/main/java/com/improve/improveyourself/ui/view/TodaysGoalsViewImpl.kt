package com.improve.improveyourself.ui.view

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.adapter.CurrentGoalAdapter
import com.improve.improveyourself.ui.controller.TodaysGoalsController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_tomorrows_goals.*

/**
 * Created by konk3r on 2/11/18.
 */
class TodaysGoalsViewImpl(override val containerView: View, val controller: TodaysGoalsController) :
        LayoutContainer, TodaysGoalsView {

    private var goalAdapter: CurrentGoalAdapter

    init {
        goalAdapter = CurrentGoalAdapter()
        view_tomorrows_goals_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = goalAdapter
        }

        view_tomorrows_goals_fab.setOnClickListener { controller.onFabClicked() }
    }

    override fun displayList(list: MutableList<Goal>) {
        goalAdapter.setList(list)
    }
}