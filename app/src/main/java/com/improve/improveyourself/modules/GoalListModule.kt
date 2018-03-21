package com.improve.improveyourself.modules

import android.view.View
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.ui.adapter.GoalAdapter
import com.improve.improveyourself.ui.adapter.GoalHistoryAdapter
import com.improve.improveyourself.ui.navigation.FabListener
import com.improve.improveyourself.ui.view.GoalHistoryView
import com.improve.improveyourself.ui.view.GoalHistoryViewImpl
import com.improve.improveyourself.ui.view.GoalListView
import com.improve.improveyourself.ui.view.GoalListViewImpl
import dagger.Module
import dagger.Provides

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class GoalListModule(val view: View, val fabListener: FabListener? = null) {

    @Provides
    fun provideCurrentGoalsView(goalAdapter: GoalAdapter): GoalListView {
        return GoalListViewImpl(view, fabListener!!, goalAdapter)
    }

    @Provides
    fun provideGoalHistoryView(goalAdapter: GoalHistoryAdapter): GoalHistoryView {
        return GoalHistoryViewImpl(view, goalAdapter)
    }

    @Provides
    fun provideCurrentGoalsAdapter(goalManager: GoalManager): GoalAdapter {
        return GoalAdapter(goalManager)
    }

    @Provides
    fun provideGoalHistoryAdapter(goalManager: GoalManager): GoalHistoryAdapter {
        return GoalHistoryAdapter(goalManager)
    }

}