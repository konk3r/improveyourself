package com.improve.improveyourself.modules

import android.view.View
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.ui.adapter.GoalAdapter
import com.improve.improveyourself.ui.controller.GoalListController
import com.improve.improveyourself.ui.view.GoalListView
import com.improve.improveyourself.ui.view.GoalListViewImpl
import dagger.Module
import dagger.Provides

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class GoalListModule(val view: View, val controller: GoalListController) {

    @Provides
    fun providesTomorrowsGoalView(goalAdapter: GoalAdapter): GoalListView {
        return GoalListViewImpl(view, controller, goalAdapter)
    }

    @Provides
    fun providesGoalAdapter(goalManager: GoalManager): GoalAdapter {
        return GoalAdapter(goalManager)
    }

}