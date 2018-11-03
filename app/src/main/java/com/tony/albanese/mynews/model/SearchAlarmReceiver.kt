package com.tony.albanese.mynews.model

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.activities.MainActivity
import com.tony.albanese.mynews.controller.utilities.CUSTOM_SEARCH_TAB
import com.tony.albanese.mynews.controller.utilities.NOTIFICATION_CHANNEL_ID
import com.tony.albanese.mynews.controller.utilities.SEARCH_ALARM_CODE
import com.tony.albanese.mynews.controller.utilities.TAB

class SearchAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        sendNotification(context!!)

    }

    fun sendNotification(context: Context) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.putExtra(TAB, CUSTOM_SEARCH_TAB)
        val notificationPendingIntent = PendingIntent.getActivity(context, SEARCH_ALARM_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        builder.setSmallIcon(R.drawable.ic_stat_new_article)
                .setContentTitle("My News App Messahr")
                .setContentText("You have new articles.")
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)

        notificationManager.notify(SEARCH_ALARM_CODE, builder.build());


    }
}