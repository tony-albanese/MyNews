package com.tony.albanese.mynews.controller.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

        setTab()

    }

    //Create the menu for the Activity.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_item_search -> {
                val intent = Intent(this, CustomSearchActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.menu_item_notifications -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun setTab() {
        val selectedTab = getIntent().getIntExtra("TAB", 0)
        pager.setCurrentItem(selectedTab)
    }

}
