package com.tony.albanese.mynews.model

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.tony.albanese.mynews.R

class SearchAlarmReceiver : BroadcastReceiver() {

    val channelName = "MyNews"
    val channelId = "NEWS_CHANNEL"
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor = R.color.colorPrimary
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        }


    }
}