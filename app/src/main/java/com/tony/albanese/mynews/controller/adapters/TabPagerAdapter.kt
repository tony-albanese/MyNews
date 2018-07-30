package com.tony.albanese.mynews.controller.adapters

import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tony.albanese.mynews.R
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


    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return Resources.getSystem().getString(R.string.top_stories_tab_title)
            1 -> return Resources.getSystem().getString(R.string.most_read_tab_title)
            2 -> return Resources.getSystem().getString(R.string.science_tab_title)
            else -> return "No title found."

        }
    }
}