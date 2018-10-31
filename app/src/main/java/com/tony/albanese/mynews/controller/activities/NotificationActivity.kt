package com.tony.albanese.mynews.controller.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.utilities.SEARCH_ALARM_CODE
import com.tony.albanese.mynews.model.SearchAlarmReceiver
import kotlinx.android.synthetic.main.search_parameters_layout.*

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_parameters_layout)

        btn_search.visibility = View.GONE
        tv_start_date.visibility = View.INVISIBLE
        tv_end_date.visibility = View.INVISIBLE
        tv_notification.visibility = View.VISIBLE
        switch_auto_search.visibility = View.VISIBLE
    }


    fun startSearchAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, SearchAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, SEARCH_ALARM_CODE, intent, 0)
        //alarmManager.setExact() -- Set this method when you have notifications ready.
    }

    fun cancelSearchAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, SearchAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, SEARCH_ALARM_CODE, intent, 0)
        alarmManager.cancel(pendingIntent)
    }
}
