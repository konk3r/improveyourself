package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.navigation.ToolbarManager
import dagger.Module
import dagger.Provides

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class TabContainerModule(val router: MainRouter, val toolbarManager: ToolbarManager) {

    @Provides
    fun providesMainRouter() = router

    @Provides
    fun providesToolbarManager() = toolbarManager

}