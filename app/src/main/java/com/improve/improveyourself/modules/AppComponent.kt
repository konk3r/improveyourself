package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.ImproveApp
import dagger.Component
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,
        LocalDataModule::class,
        GoalModule::class))
interface AppComponent {
    fun inject(app: ImproveApp)
    fun plus(mainModule: MainModule): MainComponent
}