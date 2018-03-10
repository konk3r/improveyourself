package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.controller.GoalHistoryController
import com.improve.improveyourself.ui.controller.GoalListController
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Singleton
@Subcomponent(modules = arrayOf(GoalListModule::class))
interface GoalListComponent {
    fun inject(controller: GoalListController)
    fun inject(controller: GoalHistoryController)
}