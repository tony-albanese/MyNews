package com.tony.albanese.mynews.controller.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.fragments.ArticleFragment
import com.tony.albanese.mynews.controller.fragments.CustomSearchFragment
import com.tony.albanese.mynews.controller.utilities.*

class TabPagerAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    val mContext = context
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(postion: Int): Fragment {
        when (postion) {
            0 -> return ArticleFragment().newInstance(TOP_STORIES_SEARCH, TOP_STORIES_RESULTS, TOP_STORIES_KEY)
            1 -> return ArticleFragment().newInstance(MOST_POPULAR_SEARCH, MOST_POPULAR_RESULTS, MOST_POPULAR_KEY)
            2 -> return ArticleFragment().newInstance(TOP_SCIENCE_SEARCH, TOP_SCIENCE_RESULTS, TOP_SCIENCE_KEY)
            3 -> return CustomSearchFragment()
            else -> return ArticleFragment().newInstance(TOP_STORIES_SEARCH, TOP_STORIES_RESULTS, TOP_STORIES_KEY)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext.getString(R.string.top_stories_tab_title)
            1 -> return mContext.getString(R.string.most_read_tab_title)
            2 -> return mContext.getString(R.string.science_tab_title)
            3 -> return mContext.getString(R.string.custom_search_title)
            else -> return mContext.getString(R.string.default_title)
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}