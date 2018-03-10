package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.controller.CreateGoalController
import com.improve.improveyourself.ui.controller.TabContainerController
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Singleton
@Subcomponent(modules = arrayOf(TabContainerModule::class))
interface TabContainerComponent {
    fun inject(controller: TabContainerController)
    fun inject(controller: CreateGoalController)

    fun plus(goalListModule: GoalListModule): GoalListComponent
}