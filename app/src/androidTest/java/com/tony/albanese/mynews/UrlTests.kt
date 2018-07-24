package com.tony.albanese.mynews


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.createSearchParametersJson
import com.tony.albanese.mynews.controller.generateSearchUrl
import com.tony.albanese.mynews.controller.stringToUrl
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UrlTests {
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun testValidUrl() {
        val goodUrl = "http://www.google.de"
        Assert.assertEquals(goodUrl, stringToUrl(goodUrl).toString())
    }

    @Test
    fun badUrlTest() {
        val badUrl = "htt www.foo" //A malformed url.
        val result = stringToUrl(badUrl)
        Assert.assertNull(result)
    }

    @Test
    fun testTopStoriesUrl() {
        val expectedUrl = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=8768b5f889974203a05b462b8b7dc800"
        val url = generateSearchUrl(appContext, 2)
        assertEquals(expectedUrl, url)
    }

    @Test
    fun testMostPopularUrl() {
        val expectedUrl = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=8768b5f889974203a05b462b8b7dc800"
        val url = generateSearchUrl(appContext, 1)
        assertEquals(expectedUrl, url)
    }

    @Test
    fun testScienceUrl() {
        val expectedUrl = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/Science/1.json?api-key=8768b5f889974203a05b462b8b7dc800"
        val url = generateSearchUrl(appContext, 3)
        assertEquals(expectedUrl, url)
    }

    @Test
    fun testCustomSearchUrl() {
        val parameters = createSearchParametersJson("us trade deficit", "20161010", "20171010", "Business Foreign")
        val url = generateSearchUrl(appContext, 4, parameters)
        assertEquals("Method 4 called.", url)
        //TODO Actually test the url produced with assert.
    }

    @Test
    fun testJsonObjectFunction() {
        //TODO: Implement test for the function that generates the JSON.
    }
}