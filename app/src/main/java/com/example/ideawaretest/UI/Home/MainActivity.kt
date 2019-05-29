package com.example.ideawaretest.UI.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ideawaretest.R
import com.example.ideawaretest.UI.Home.Adapters.HomeFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view_pager.adapter = HomeFragmentAdapter(supportFragmentManager)
        main_tabs.setupWithViewPager(main_view_pager)

    }
}
