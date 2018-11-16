package com.tony.albanese.mynews.unit_tests

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.connectToSite
import com.tony.albanese.mynews.controller.utilities.stringToUrl
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URL
import java.net.URLConnection

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//These tests are to check the functions that implement connecting to servers.
@RunWith(AndroidJUnit4::class)
class ConnectionControllerUnitTests {

    val testWebSite = "http://echo.jsontest.com/key/value/one/two"
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tony.albanese.mynews", appContext.packageName)
    }

    @Test //Test case: user enters a valid url string.
    fun testValidUrl() {
        val goodUrl = "http://www.google.de"
        Assert.assertEquals(goodUrl, stringToUrl(goodUrl).toString())
    }

    @Test //Test case: user enters invalid url
    fun badUrlTest() {
        val badUrl = "htt www.foo" //A malformed url.
        val result = stringToUrl(badUrl)
        Assert.assertNull(result)
    }

    @Test //Test case: connecting with valid url.
    fun testGoodConnection() {
        val website = testWebSite
        val url = stringToUrl(website)
        val connection = connectToSite(url!!)
        assertEquals(connection.toString(), url.openConnection().toString())
    }

    @Test //Test case: connecting with invalid url.
    fun testBadConnection() {
        val badSite = "hrr www ff"
        val badUrl: URL? = stringToUrl(badSite)
        val badConnection: URLConnection? = badUrl?.openConnection()
        assertNull(badConnection)
    }
}
