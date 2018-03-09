package com.improve.improveyourself.modules

import com.boofr.android.modules.LocalDataModule
import com.improve.improveyourself.ui.ImproveApp
import dagger.Component
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,
        UserModule::class,
        LocalDataModule::class))
interface AppComponent {
    fun inject(app: ImproveApp)
    fun plus(mainModule: MainModule): MainComponent
}