package com.example.ideawaretest.UI.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.ideawaretest.Data.Local.Group
import com.example.ideawaretest.Data.Local.Results
import com.example.ideawaretest.R
import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems
import java.text.SimpleDateFormat
import java.util.*

class ResultsAdapter(private val list: MutableList<HomeItems>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeItems.TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_header, parent, false))
            HomeItems.TYPE_RESULTS -> ResulstsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_results, parent, false))
            else -> throw IllegalStateException("Unsupported item type")
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = list[position].getId()

    override fun getItemViewType(position: Int): Int = list[position].getType()

    fun appendNewItems(itemsToAppend: MutableList<HomeItems>){
        list.addAll(itemsToAppend)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HomeItems.TYPE_HEADER -> {
                val header = list[position] as Group
                (holder as HeaderViewHolder).bind(header)
            }
            HomeItems.TYPE_RESULTS -> {
                val results = list[position] as Results
                (holder as ResulstsViewHolder).bind(results)
            }
            else -> throw IllegalStateException("Unsupported item Type")
        }
    }

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val month = view.findViewById<TextView>(R.id.header_month)

        fun bind(group: Group) {
            month.text = group.date
        }
    }

    inner class ResulstsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val competition = view.findViewById<TextView>(R.id.result_competition)
        private val venue = view.findViewById<TextView>(R.id.result_venue)
        private val date = view.findViewById<TextView>(R.id.result_date)
        private val homeTeam = view.findViewById<TextView>(R.id.result_home_team)
        private val awayTeam = view.findViewById<TextView>(R.id.result_away_team)
        private val homeScore = view.findViewById<TextView>(R.id.result_home_score)
        private val awayScore = view.findViewById<TextView>(R.id.result_away_score)

        fun bind(results: Results) {

            competition.text = results.competitionState.competition.competition_name
            venue.text = results.venue.venue_name
            homeTeam.text = results.homeTeam.home_shortName
            awayTeam.text = results.awayTeam.away_shortName

            val format = SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'", Locale.getDefault())
            val format2 = SimpleDateFormat("MMM d, yyy 'at' HH:mm")

            val newDate: Date = format.parse(results.date)
            date.text = format2.format(newDate.time)

            when {
                results.score.score_home > results.score.score_away -> {
                    homeScore.setTextColor("#1c7bbf".toColorInt())
                    awayScore.setTextColor("#000000".toColorInt())
                }
                results.score.score_home == results.score.score_away -> {
                    homeScore.setTextColor("#000000".toColorInt())
                    awayScore.setTextColor("#000000".toColorInt())
                }
                results.score.score_home < results.score.score_away -> {
                    awayScore.setTextColor("#1c7bbf".toColorInt())
                    homeScore.setTextColor("#000000".toColorInt())
                }
            }
            homeScore.text = results.score.score_home.toString()
            awayScore.text = results.score.score_away.toString()

        }

    }
}