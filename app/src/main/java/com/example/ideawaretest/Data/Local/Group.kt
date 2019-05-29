package com.example.ideawaretest.Data.Local

import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems

data class Group(
    var group_id: Long,
    var month: String,
    var year: String
): HomeItems() {
    override fun getType(): Int = TYPE_HEADER

    override fun getId(): Long = group_id
}