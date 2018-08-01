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

        val url = generateSearchUrl(appContext,1)
        //Make the connection and get the response.
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        //Call generateArticleArray() to create the ArrayList
        val list = generateArticleArray(1,response)
        assertEquals(1, list.size)
    }

}