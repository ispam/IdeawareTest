package com.example.ideawaretest.UI

import android.app.Application
import com.example.ideawaretest.DI.AppComponent
import com.example.ideawaretest.DI.AppModule
import com.example.ideawaretest.DI.DaggerAppComponent

class App: Application() {

    companion object {
        var app: App? = null
        @JvmStatic
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        app = this

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}