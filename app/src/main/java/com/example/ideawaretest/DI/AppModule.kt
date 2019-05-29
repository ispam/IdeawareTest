package com.example.ideawaretest.DI

import android.content.Context
import com.example.ideawaretest.UI.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun getApplication(): Context = app
}