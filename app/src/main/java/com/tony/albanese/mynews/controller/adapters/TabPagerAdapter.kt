package com.tony.albanese.mynews.controller.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.fragments.*
import com.tony.albanese.mynews.controller.utilities.TOP_STORIES_KEY
import com.tony.albanese.mynews.controller.utilities.TOP_STORIES_RESULTS
import com.tony.albanese.mynews.controller.utilities.TOP_STORIES_SEARCH

class TabPagerAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    val mContext = context
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(postion: Int): Fragment {
        when (postion) {
            0 -> return ArticleFragment().newInstance(TOP_STORIES_SEARCH, TOP_STORIES_RESULTS, TOP_STORIES_KEY)
            // 0 -> return TopStoriesFragment()
            1 -> return MostPopularFragment()
            2 -> return TopScienceStoriesFragment()
            3 -> return CustomSearchFragment()
            else -> return TopStoriesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext.getString(R.string.top_stories_tab_title)
            1 -> return mContext.getString(R.string.most_read_tab_title)
            2 -> return mContext.getString(R.string.science_tab_title)
            3 -> return mContext.getString(R.string.custom_search_title)
            else -> return "No title found."

        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}