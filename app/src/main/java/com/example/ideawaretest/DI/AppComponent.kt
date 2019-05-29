package com.example.ideawaretest.DI

import com.example.ideawaretest.UI.Home.FixturesFragment
import com.example.ideawaretest.UI.Home.MainActivity
import com.example.ideawaretest.UI.Home.ResultsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(resultsFragment: ResultsFragment)

    fun inject(fixturesFragment: FixturesFragment)
}