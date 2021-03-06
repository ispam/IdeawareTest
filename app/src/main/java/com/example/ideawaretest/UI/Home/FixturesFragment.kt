package com.example.ideawaretest.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ideawaretest.Data.Local.Fixtures
import com.example.ideawaretest.Data.Local.Group
import com.example.ideawaretest.Data.Remote.APIService
import com.example.ideawaretest.R
import com.example.ideawaretest.UI.App
import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems
import com.example.ideawaretest.UI.Home.Adapters.FixturesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_fixtures.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FixturesFragment: Fragment() {

    private val disposable = CompositeDisposable()
    private var firstTime = true

    @Inject
    lateinit var apiService: APIService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fixtures_recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        disposable.add(apiService.getFixtures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {

                val groups = it.groupBy { item ->
                    val format = SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'", Locale.getDefault())
                    val newDate: Date = format.parse(item.date)
                    val monthFormat = SimpleDateFormat("MMMM, yyy")
                    monthFormat.format(newDate.time)
                }

                val newList = mutableListOf<HomeItems>()
                for (entry in groups.entries) {
                    val random = Random().nextInt(10000)
                    val group = Group(random.toLong(), entry.key)

                    newList.add(group)
                    for (fixture in entry.value) {
                        if (fixture.state.isNullOrEmpty()) {
                            fixture.state = ""
                        }
                        newList.add(Fixtures(fixture.fixture_id, fixture.type, fixture.homeTeam, fixture.awayTeam, fixture.date, fixture.competitionState, fixture.venue, fixture.state))
                    }
                }

                val adapter = FixturesAdapter(newList)
                fixtures_recycler.adapter = adapter

                val spinnerGroup = it.groupBy { it.competitionState.competition.competition_name }

                val spinnerList = mutableListOf<String>()
                for (fixture in spinnerGroup.keys) {
                    spinnerList.add(fixture)
                }

                fixtures_spinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerList)
                fixtures_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        if (!firstTime) {
                            val competition = spinnerList[position]

                            val competitionItems = it.filter { it.competitionState.competition.competition_name == competition }

                            adapter.clearList()
                            adapter.appendNewItems(competitionItems as MutableList<HomeItems>)
                        } else {
                           firstTime = false
                        }
                    }

                }
            }
            .subscribe())
    }

    override fun onStop() {
        if (!disposable.isDisposed) disposable.clear()
        super.onStop()
    }
}