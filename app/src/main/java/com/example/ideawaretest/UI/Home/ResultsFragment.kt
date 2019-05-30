package com.example.ideawaretest.UI.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ideawaretest.Data.Local.Group
import com.example.ideawaretest.Data.Local.Results
import com.example.ideawaretest.Data.Remote.APIService
import com.example.ideawaretest.R
import com.example.ideawaretest.UI.App
import com.example.ideawaretest.UI.Home.Adapters.Extras.HomeItems
import com.example.ideawaretest.UI.Home.Adapters.ResultsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_results.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResultsFragment: Fragment() {

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var apiService: APIService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        results_recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        disposable.add(apiService.getResults()
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
                    for (result in entry.value) {
                        if (result.state.isNullOrEmpty()) {
                            result.state = ""
                        }
                        newList.add(Results(result.result_id, result.type, result.homeTeam, result.awayTeam, result.date, result.competitionState, result.venue, result.state, result.score))
                    }
                }

                results_recycler.adapter = ResultsAdapter(newList)
            }
            .subscribe())
    }

    override fun onStop() {
        if (!disposable.isDisposed) disposable.clear()
        super.onStop()
    }
}