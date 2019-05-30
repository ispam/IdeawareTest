package com.example.ideawaretest.UI.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.ideawaretest.Data.Local.Fixtures
import com.example.ideawaretest.Data.Local.Group
import com.example.ideawaretest.R
import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems
import java.text.SimpleDateFormat
import java.util.*

class FixturesAdapter(private val list: MutableList<HomeItems>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                val fixtures = list[position] as Fixtures
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
        private val venue = view.findViewById<TextView>(R.id.fixture_venue)
        private val date = view.findViewById<TextView>(R.id.fixture_date)
        private val homeTeam = view.findViewById<TextView>(R.id.fixture_home_team)
        private val awayTeam = view.findViewById<TextView>(R.id.fixture_away_team)
        private val day = view.findViewById<TextView>(R.id.fixture_day)
        private val dayShort = view.findViewById<TextView>(R.id.fixture_day_short)
        private val postponed = view.findViewById<TextView>(R.id.fixture_postponed)

        fun bind(fixtures: Fixtures) {

            competition.text = fixtures.competitionState.competition.competition_name
            venue.text = fixtures.venue.venue_name
            homeTeam.text = fixtures.homeTeam.home_shortName
            awayTeam.text = fixtures.awayTeam.away_shortName


            if (fixtures.state == "postponed") {
                postponed.visibility = View.VISIBLE
                date.setTextColor("#CC4700".toColorInt())
            } else {
                postponed.visibility = View.INVISIBLE
                date.setTextColor("#8A8A8A".toColorInt())
            }

            val format = SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'", Locale.getDefault())
            val format2 = SimpleDateFormat("MMM d, yyy 'at' HH:mm")

            val newDate:Date = format.parse(fixtures.date)
            date.text = format2.format(newDate.time)

            val monthFormat = SimpleDateFormat("EE")
            val dayFormat = SimpleDateFormat("d")

            day.text = dayFormat.format(newDate.time)
            dayShort.text = monthFormat.format(newDate.time)

        }

    }
}