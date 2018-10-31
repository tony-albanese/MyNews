package com.tony.albanese.mynews.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class SearchAlarmReceiver : BroadcastReceiver() {

    val channelName = "MyNews"
    val channelDescription = "Description from NewsApp"
    val channelId = "NEWS_CHANNEL"
    override fun onReceive(context: Context?, intent: Intent?) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

}