package com.example.ideawaretest.Data.Local

import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

data class Results(
    @SerializedName("id")
    var result_id: Long,
    @SerializedName("type")
    var type: String,
    @SerializedName("homeTeam")
    var homeTeam: @RawValue HomeTeam,
    @SerializedName("awayTeam")
    var awayTeam: @RawValue AwayTeam,
    @SerializedName("date")
    var date: String,
    @SerializedName("competitionStage")
    var competitionState: @RawValue CompetitionStage,
    @SerializedName("venue")
    var venue: @RawValue Venue,
    @SerializedName("state")
    var state: String,
    @SerializedName("score")
    var score: @RawValue Score
): HomeItems() {
    override fun getType(): Int = TYPE_RESULTS

    override fun getId(): Long = result_id
}

data class Score(
    @SerializedName("home")
    var score_home: Int,
    @SerializedName("away")
    var score_away: Int,
    @SerializedName("winner")
    var score_winner: String
)