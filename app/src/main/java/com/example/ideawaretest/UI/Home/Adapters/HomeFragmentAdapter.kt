package com.example.ideawaretest.UI.Home.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.ideawaretest.UI.Home.FixturesFragment
import com.example.ideawaretest.UI.Home.ResultsFragment

class HomeFragmentAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    companion object {
        private const val NUM = 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Fixtures"
            1 -> return "Results"
        }

        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FixturesFragment()
            1 -> return ResultsFragment()
        }
        return null
    }

    override fun getCount(): Int = NUM
}