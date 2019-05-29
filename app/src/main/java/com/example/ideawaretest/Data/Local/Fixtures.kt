package com.example.ideawaretest.Data.Local

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

data class Fixtures(
    @SerializedName("id")
    var fixture_id: Int,
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
    var state: String
)

data class HomeTeam(
    @SerializedName("id")
    var home_id: Int,
    @SerializedName("name")
    var home_name: String,
    @SerializedName("shortName")
    var home_shortName: String,
    @SerializedName("abbr")
    var home_abbr: String,
    @SerializedName("alias")
    var home_alias: String
)

data class AwayTeam(
    @SerializedName("id")
    var away_id: Int,
    @SerializedName("name")
    var away_name: String,
    @SerializedName("shortName")
    var away_shortName: String,
    @SerializedName("abbr")
    var away_abbr: String,
    @SerializedName("alias")
    var away_alias: String
)

data class CompetitionStage(
    @SerializedName("competition")
    var competition: Competition
)

data class Competition(
    @SerializedName("id")
    var competition_id: Int,
    @SerializedName("name")
    var competition_name: String
)

data class Venue(
    @SerializedName("id")
    var venue_id: Int,
    @SerializedName("name")
    var venue_name: String
)