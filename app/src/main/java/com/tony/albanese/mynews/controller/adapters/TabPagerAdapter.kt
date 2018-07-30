package com.tony.albanese.mynews.controller.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tony.albanese.mynews.controller.fragments.MostPopularFragment
import com.tony.albanese.mynews.controller.fragments.TopScienceStoriesFragment
import com.tony.albanese.mynews.controller.fragments.TopStoriesFragment

class TabPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3 //TODO: Incorporate this parameter within the constructor.
    }

    override fun getItem(postion: Int): Fragment {
        when (postion) {
            0 -> return TopStoriesFragment()
            1 -> return MostPopularFragment()
            2 -> return TopScienceStoriesFragment()
            else -> return TopStoriesFragment()
        }
    }


}