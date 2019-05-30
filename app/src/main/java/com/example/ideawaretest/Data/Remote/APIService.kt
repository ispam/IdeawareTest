package com.example.ideawaretest.Data.Remote

import com.example.ideawaretest.Data.Local.Fixtures
import com.example.ideawaretest.Data.Local.Results
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

    @GET("cdn-og-test-api/test-task/fixtures.json")
    fun getFixtures(): Single<List<Fixtures>>

    @GET("cdn-og-test-api/test-task/results.json")
    fun getResults(): Single<List<Results>>
}