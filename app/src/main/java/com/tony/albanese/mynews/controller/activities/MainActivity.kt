package com.tony.albanese.mynews.controller.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.adapters.TabPagerAdapter

class MainActivity : AppCompatActivity() {

    //Declare references for the pager adapter, the view pager, and the tab layout objects.
    lateinit var pagerAdapter: TabPagerAdapter
    lateinit var pager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = TabPagerAdapter(supportFragmentManager, applicationContext) //Initialize
        pager = findViewById(R.id.view_pager_main_activity) //Initialize
        tabLayout = findViewById(R.id.tab_layout_main_activity) //Initialize

        pager.adapter = pagerAdapter //Set the adapter.
        tabLayout.setupWithViewPager(pager) //Attach the pager to the tab layout.

    }
}
