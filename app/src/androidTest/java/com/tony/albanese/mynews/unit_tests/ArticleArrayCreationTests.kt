package com.tony.albanese.mynews.unit_tests

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleArrayCreationTests {
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun testMostPopularArrayCreation(){
    val url = generateSearchUrl(appContext, 1)
            val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(1, response)
        assertEquals(20, list.size)
    }

    @Test
    fun testTopStoriesArrayCreation(){
        val url = generateSearchUrl(appContext, 2)
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(2, response)
        assertEquals(45, list.size)
    }

    @Test
    fun testScienceTopStoriesArrayCreation(){
        val url = generateSearchUrl(appContext, 3)
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(3, response)
        //assertEquals(45, list.size)
    }

}