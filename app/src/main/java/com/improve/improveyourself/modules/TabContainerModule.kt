package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.navigation.MainRouter
import dagger.Module
import dagger.Provides

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class TabContainerModule(val router: MainRouter) {

    @Provides
    fun providesMainRouter() = router

}