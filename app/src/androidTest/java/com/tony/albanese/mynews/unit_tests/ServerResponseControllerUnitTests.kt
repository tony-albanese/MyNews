package com.tony.albanese.mynews.unit_tests

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServerResponseControllerUnitTests {
    /*
    Intialize the appContext objects and some test lists for unit tests.
     */
    val appContext = InstrumentationRegistry.getTargetContext()

    var list1 = ArrayList<Article>()
    var list2 = ArrayList<Article>()
    var list3 = ArrayList<Article>()

    val article1 = Article("One", "One", "One", "One", "One")
    val article2 = Article("Two", "Two", "Two", "Two", "Two")
    val article3 = Article("Three", "Three", "Three", "Three", "Three")
    @Before
    fun init() {
        list1.add(article1)
        list1.add(article2)

        list2.add(article1)
        list2.add(article3)

        list3.add(article1)
        list3.add(article2)
        list3.add(article3)
    }

    @Test
    fun testUpdateArrayListSize() {
        val testList = updateArrayList(list1, list2)
        assertEquals(list3.size, testList.size)
    }

    @Test
    fun testUpdateArrayListMembers() {
        val testList = updateArrayList(list1, list2)
        val testArticle = testList[0]
        val expectedArticle = list2[1]
        assertEquals(expectedArticle.mTitle, testArticle.mTitle)
    }

    @Test
    fun testMostPopularArrayCreation() {
        val url = generateSearchUrl(appContext, 1)
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(1, response)
        assertEquals(20, list.size)
    }

    @Test
    fun testTopStoriesArrayCreation() {
        val url = generateSearchUrl(appContext, 2)
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(2, response)
        val obj = JSONObject(response)
        val number_results = obj.getString("num_results")
        assertEquals(number_results, list.size.toString())
    }

    @Test
    fun testScienceTopStoriesArrayCreation() {
        val url = generateSearchUrl(appContext, 3)
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(1, response)
        assertEquals(20, list.size)
    }

    @Test
    fun testCustomSearch(){
        val parameters = createSearchParametersJson("us trade deficit", "20161010", "20171010", "Business Foreign")
        val url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=8768b5f889974203a05b462b8b7dc800&q=trump&fq=Business%20Politics&begin_date=20100909&end_date=20180909"
        val connection = connectToSite(stringToUrl(url)!!)
        val response = readDataFromConnection(connection!!)
        val list = generateArticleArray(3, response)
        assertEquals(10, list.size)
    }
}