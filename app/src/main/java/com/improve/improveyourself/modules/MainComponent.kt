package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.controller.DashboardController
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Singleton
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(controller: DashboardController)

    fun plus(tabContainerModule: TabContainerModule): TabContainerComponent
}