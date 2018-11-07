package com.tony.albanese.mynews.integration_tests

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.tony.albanese.mynews.controller.utilities.ARTICLE_PREFERENCES
import com.tony.albanese.mynews.model.Article
import org.junit.Before
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class SharedPreferenesTests {
    lateinit var preferences: SharedPreferences
    lateinit var context: Context

    var list1 = ArrayList<Article>()
    var list2 = ArrayList<Article>()

    val gson = Gson()
    val url1 = ""
    val url2 = ""

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getContext()
        preferences = context.getSharedPreferences(ARTICLE_PREFERENCES, Context.MODE_PRIVATE)

        val connection = HttpURLConnection()

    }

}