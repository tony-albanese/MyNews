package com.tony.albanese.mynews.unit_tests
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.createSearchParametersJson
import com.tony.albanese.mynews.controller.utilities.generateSearchUrl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

//These tests check functions that build the search urls to query the API.
@RunWith(AndroidJUnit4::class)
class SearchUrlFormationUnitTest {
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test //Test case: Generate top stories search url
    fun testTopStoriesUrl() {
        val expectedUrl = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=8768b5f889974203a05b462b8b7dc800"
        val url = generateSearchUrl(appContext, 2)
        assertEquals(expectedUrl, url)
    }

    @Test //Test case: generate Most Popular search url
    fun testMostPopularUrl() {
        val expectedUrl = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=8768b5f889974203a05b462b8b7dc800"
        val url = generateSearchUrl(appContext, 1)
        assertEquals(expectedUrl, url)
    }

    @Test //Test case: Generate science search url
    fun testScienceUrl() {
        val expectedUrl = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/Science/1.json?api-key=8768b5f889974203a05b462b8b7dc800"
        val url = generateSearchUrl(appContext, 3)
        assertEquals(expectedUrl, url)
    }

    @Test //Test case: user enters search terms for article search with start and end date.
    fun testCustomSearchUrl() {
        val parameters = createSearchParametersJson("us trade deficit", "20161010", "20171010", "Business Foreign")
        val url = generateSearchUrl(appContext, 4, parameters)
        val expectedUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=8768b5f889974203a05b462b8b7dc800&q=us%20trade%20deficit&fq=Business%20Foreign&begin_date=20161010&end_date=20171010"
        assertEquals(expectedUrl, url)
    }
}