package com.tony.albanese.mynews.model

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.tony.albanese.mynews.controller.activities.MainActivity
import com.tony.albanese.mynews.controller.utilities.NOTIFICATION_CHANNEL_ID
import com.tony.albanese.mynews.controller.utilities.SEARCH_ALARM_CODE

class SearchAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        sendNotification(context!!)

    }

    fun sendNotification(context: Context) {
        val notificationIntent = Intent(context, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(context, SEARCH_ALARM_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        

    }
}