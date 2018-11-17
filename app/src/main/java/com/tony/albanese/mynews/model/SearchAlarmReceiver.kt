package com.tony.albanese.mynews.model

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.activities.MainActivity
import com.tony.albanese.mynews.controller.utilities.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.HttpURLConnection
import java.util.*

class SearchAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val searchTerms = intent?.getStringExtra(SEARCH_TERMS_INTENT_KEY)
        val newsDesks = intent?.getStringExtra(NEWS_DESK_INTENT_KEY)

        val date = Calendar.getInstance().time
        val searchStartDate = convertDate(date, SEARCH_DATE_FORMAT)
        val jsonPrameters = createSearchParametersJson(searchTerms
                ?: "", searchStartDate, "", newsDesks ?: "")
        val url = generateSearchUrl(context!!, CUSTOM_SEARCH_SEARCH, jsonPrameters)
        if (url != null && url.isNotEmpty() && url.isNotBlank()) {
            getNewArticles(context!!, url)
        }

    }

    fun sendNotification(context: Context) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.putExtra(TAB, CUSTOM_SEARCH_TAB)
        val notificationPendingIntent = PendingIntent.getActivity(context, SEARCH_ALARM_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        builder.setSmallIcon(R.drawable.ic_stat_new_article)
                .setContentTitle("MyNews App Messanger")
                .setContentText("You have new articles.")
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        notificationManager.notify(SEARCH_ALARM_CODE, builder.build());
    }

    fun getNewArticles(c: Context, url: String) {
        var connection: HttpURLConnection?
        var result = ""
        if (networkIsAvailable(c)) {
            connection = connectToSite(stringToUrl(url)!!)

            doAsync {
                if (connection != null) {
                    result = readDataFromConnection(connection)
                }
                uiThread {
                    val tempList = generateArticleArray(CUSTOM_SEARCH_RESULTS, result)
                    //list = tempList
                    if (tempList.isNotEmpty()) {
                        val prefs = c.getSharedPreferences(ARTICLE_PREFERENCES, MODE_PRIVATE)
                        saveArrayListToSharedPreferences(prefs, NEW_ARTICLE_KEY, tempList)
                        sendNotification(c!!)
                    }
                }
            }
        }
    }
}