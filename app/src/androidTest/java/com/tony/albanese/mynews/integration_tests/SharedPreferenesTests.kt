package com.tony.albanese.mynews.integration_tests

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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
    var url1 = ""
    var url2 = ""

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getContext()
        preferences = context.getSharedPreferences(ARTICLE_PREFERENCES, Context.MODE_PRIVATE)

        url1 = generateSearchUrl(context, MOST_POPULAR_SEARCH)
        url2 = generateSearchUrl(context, TOP_SCIENCE_SEARCH)
        val connection = connectToSite(stringToUrl(url1)!!)
        val connection2 = connectToSite(stringToUrl(url2)!!)

        list1 = getArticles(connection!!, MOST_POPULAR_SEARCH)
        list2 = getArticles(connection2!!, TOP_SCIENCE_RESULTS)

    }

    fun getArticles(connection: HttpURLConnection, resultType: Int): ArrayList<Article> {
        var result = ""
        var list = ArrayList<Article>()
        doAsync {
            result = readDataFromConnection(connection)
            uiThread {
                list = generateArticleArray(resultType, result)
            }
        }
        return list
    }

    
}