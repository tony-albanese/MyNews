package com.tony.albanese.mynews

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.connectToSite
import com.tony.albanese.mynews.controller.utilities.readDataFromConnection
import com.tony.albanese.mynews.controller.utilities.stringToUrl
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
class ConnectionUnitTest {

    val testWebSite = "http://echo.jsontest.com/key/value/one/two"
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tony.albanese.mynews", appContext.packageName)
    }

    @Test //Validates output of stringToUrl() function. Test case: valid url.
    fun validUrlTest(){
        val urlString = "http://www.google.de"
        assertEquals(urlString, stringToUrl(urlString).toString())
    }

    @Test //Test stringToUrl() Test case: invalid url
    fun badUrlTest(){
        val badUrl = "htt www.goo com"
        assertEquals(null, stringToUrl(badUrl))
    }

    @Test //Test connection: Test case: connecting with valid url.
    fun testGoodConnection(){
        val website = testWebSite
        val url = stringToUrl(website)
        val connection = connectToSite(url!!)
        assertEquals(connection.toString(), url.openConnection().toString())
    }

    @Test //Test connection. Test case: connecting with invalid url.
    fun testBadConnection(){
        val badSite = "hrr www ff"
        val badUrl: URL? = stringToUrl(badSite)
        val badConnection: URLConnection? = badUrl?.openConnection()
        assertNull(badConnection)
    }

    @Test //This function tests the function that reads the response from the server with a valid REST api.
    fun testReaderResponse() {
        val url = stringToUrl("http://echo.jsontest.com/key/value/one/two")
        val connection = connectToSite(url!!)
        val response = readDataFromConnection(connection!!)
        val expectedResponse = "{\"one\": \"two\",\"key\": \"value\"}"
        assertEquals(expectedResponse.replace(" ", ""), response.replace(" ", ""))
    }
}
