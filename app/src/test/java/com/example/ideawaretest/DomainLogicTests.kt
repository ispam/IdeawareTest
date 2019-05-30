package com.example.ideawaretest

import com.example.ideawaretest.Data.Local.*
import com.example.ideawaretest.Data.Remote.APIService
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DomainLogicTests {

    val apiService = mockk<APIService>(relaxed = true)

    @BeforeEach
    fun clear() {
        clearMocks(apiService)
    }

    @Nested
    inner class FixturesFragmentTest {

        @Test
        fun `getFixtures() method is not empty`() {

            every { apiService.getFixtures() } returns Single.just(fixtureList())

            apiService.getFixtures()
                .test()
                .assertSubscribed()
                .assertNoErrors()
                .assertResult(fixtureList())
                .assertComplete()
                .dispose()

        }
    }

    @Nested
    inner class ResultsFragmentTests {

        @Test
        fun `getResults() method is not empty`() {

            every { apiService.getResults() } returns Single.just(resultsList())

            apiService.getResults()
                .test()
                .assertSubscribed()
                .assertNoErrors()
                .assertResult(resultsList())
                .assertComplete()
                .dispose()


        }

    }

    private fun fixtureList(): List<Fixtures> {

        val list = mutableListOf<Fixtures>()

        for (num in 0..10) {
            list.add(
                Fixtures(
                    num.toLong(), "type $num",
                    HomeTeam(num, "HomeTeam $num", "Home $num", "Ho$num", "Alias H$num"),
                    AwayTeam(num, "AwayTeam $num", "Away $num", "Aw$num", "Alias A$num"),
                    "11/22/1995",
                    CompetitionStage(Competition(num, "Comp $num"), "Finals", "Last round"),
                    Venue(num, "Venue $num"),
                    "playable"
                )
            )
        }

        return list
    }

    private fun resultsList(): List<Results> {

        val list = mutableListOf<Results>()

        for (num in 0..10) {
            list.add(
                Results(
                    num.toLong(), "type $num",
                    HomeTeam(num, "HomeTeam $num", "Home $num", "Ho$num", "Alias H$num"),
                    AwayTeam(num, "AwayTeam $num", "Away $num", "Aw$num", "Alias A$num"),
                    "11/22/1995",
                    CompetitionStage(Competition(num, "Comp $num"), "Finals", "Last round"),
                    Venue(num, "Venue $num"),
                    "playable",
                    Score(num+1, num, "home")
                )
            )
        }

        return list
    }
}