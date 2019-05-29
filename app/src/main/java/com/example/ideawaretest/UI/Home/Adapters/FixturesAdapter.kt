package com.example.ideawaretest.UI.Home.Adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ideawaretest.Data.Local.Fixtures

class FixturesAdapter(private val list: MutableList<Fixtures>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = list[position].getId()

    override fun getItemViewType(position: Int): Int = list[position].getType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    inner class FixturesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}