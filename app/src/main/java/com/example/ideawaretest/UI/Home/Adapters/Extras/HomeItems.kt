package com.example.ideawaretest.UI.Home.Adapters.Extras

abstract class HomeItems {

    companion object Types {
        const val TYPE_HEADER = 0
        const val TYPE_FIXTURES = 1
        const val TYPE_RESULTS = 2
    }

    abstract fun getType(): Int
    abstract fun getId(): Long
}