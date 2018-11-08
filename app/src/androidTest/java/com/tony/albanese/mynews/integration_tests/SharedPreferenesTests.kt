package com.tony.albanese.mynews.integration_tests

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferenesTests {
    lateinit var preferences: SharedPreferences
    val appContext = InstrumentationRegistry.getTargetContext()


    var list1 = ArrayList<Article>()
    var list2 = ArrayList<Article>()

    val gson = Gson()
    var url1 = ""
    var url2 = ""

    @Before
    fun setup() {
        preferences = appContext.getSharedPreferences(ARTICLE_PREFERENCES, Context.MODE_PRIVATE)
        url1 = generateSearchUrl(appContext, TOP_STORIES_SEARCH)
        url2 = generateSearchUrl(appContext, TOP_SCIENCE_SEARCH)


    }


    @Test
    fun checkArraySizeAreEqual() {
        var connection = connectToSite(stringToUrl(url1)!!)
        var result1 = readDataFromConnection(connection!!)

        connection = connectToSite(stringToUrl(url2)!!)
        var result2 = readDataFromConnection(connection!!)



        list1 = generateArticleArray(TOP_STORIES_SEARCH, result1)
        list2 = generateArticleArray(TOP_SCIENCE_RESULTS, result2)


        assertEquals(list1.size, list2.size)
    }
}