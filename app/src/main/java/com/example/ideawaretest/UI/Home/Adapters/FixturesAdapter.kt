package com.example.ideawaretest.UI.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ideawaretest.Data.Local.Fixtures
import com.example.ideawaretest.Data.Local.Group
import com.example.ideawaretest.R
import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems

class FixturesAdapter(private val list: MutableList<Fixtures>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeItems.TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_header, parent, false))
            HomeItems.TYPE_FIXTURES -> FixturesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_fixtures, parent, false))
            else -> throw IllegalStateException("Unsupported item type")
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = list[position].getId()

    override fun getItemViewType(position: Int): Int = list[position].getType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            HomeItems.TYPE_HEADER -> {
                val header = list[position] as Group
                (holder as HeaderViewHolder).bind(header)
            }
            HomeItems.TYPE_FIXTURES -> {
                val fixtures = list[position]
                (holder as FixturesViewHolder).bind(fixtures)
            }
            else -> throw IllegalStateException("Unsupported item Type")
        }
    }

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val month = view.findViewById<TextView>(R.id.header_month)
        private val year = view.findViewById<TextView>(R.id.header_year)

        fun bind(group: Group) {
            month.text = group.month
            year.text = group.year
        }

    }

    inner class FixturesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val competition = view.findViewById<TextView>(R.id.fixture_competition)
        private val venue = view.findViewById<TextView>(R.id.fixture_competition)
        private val date = view.findViewById<TextView>(R.id.fixture_competition)
        private val homeTeam = view.findViewById<TextView>(R.id.fixture_competition)
        private val awayTeam = view.findViewById<TextView>(R.id.fixture_competition)
        private val day = view.findViewById<TextView>(R.id.fixture_competition)
        private val dayShort = view.findViewById<TextView>(R.id.fixture_competition)

        fun bind(fixtures: Fixtures) {

            competition.text = fixtures.competitionState.competition.competition_name
            venue.text = fixtures.venue.venue_name
            date.text = fixtures.date
            homeTeam.text = fixtures.homeTeam.home_name
            awayTeam.text = fixtures.awayTeam.away_name


            day.text = fixtures.date
            dayShort.text = fixtures.date

        }

    }
}