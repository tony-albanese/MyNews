package com.tony.albanese.mynews.controller.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.adapters.TabPagerAdapter
import com.tony.albanese.mynews.controller.utilities.*

class MainActivity : AppCompatActivity() {

    //Declare references for the pager adapter, the view pager, and the tab layout objects.
    lateinit var pagerAdapter: TabPagerAdapter
    lateinit var pager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
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

    override fun onBackPressed() {
        if (pager.currentItem == CUSTOM_SEARCH_FRAGMENT) {
            pager.setCurrentItem(TOP_STORIES_FRAGMENT, true)
        } else {
            super.onBackPressed()
        }
    }

    //This will be called when the Activity is started to ensure a channe is set for Android 8
    fun createNotificationChannel() {
        val notfificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableVibration(false)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = R.color.colorPrimary
            notificationChannel.description = NOTIFICATION_CHANNEL_DESCRIPTION
            notfificationManager.createNotificationChannel(notificationChannel)
        }

    }
}
