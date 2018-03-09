package com.improve.improveyourself.ui.view

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.adapter.GoalAdapter
import com.improve.improveyourself.ui.controller.GoalListController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_tomorrows_goals.*

/**
 * Created by konk3r on 2/11/18.
 */
class GoalListViewImpl(override val containerView: View, val controller: GoalListController,
                       val goalAdapter: GoalAdapter) :
        LayoutContainer, GoalListView {

    init {
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