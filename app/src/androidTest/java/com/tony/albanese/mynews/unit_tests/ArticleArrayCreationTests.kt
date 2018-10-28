package com.tony.albanese.mynews.unit_tests

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.*
import junit.framework.Assert.assertEquals
import org.json.JSONObject
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
        val obj = JSONObject(response)
        val number_results = obj.getString("num_results")

        assertEquals(number_results, list.size.toString())
    }

    @Test
    fun testScienceTopStoriesArrayCreation(){
        val url = generateSearchUrl(appContext, 3)
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        //The subject search has the same structure as the most popular.
        val list = generateArticleArray(1, response)
        assertEquals(20, list.size)
    }

    @Test
    fun testCustomSearch(){
        //Create searchTerms json object
        val parameters = createSearchParametersJson("us trade deficit", "20161010", "20171010", "Business Foreign")
        //val url = generateSearchUrl(appContext, 4, parameters)

        val url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=8768b5f889974203a05b462b8b7dc800&q=trump&fq=Business%20Politics&begin_date=20100909&end_date=20180909"
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(3, response)
        assertEquals(10, list.size)
    }

}