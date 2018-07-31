package com.tony.albanese.mynews.integration_tests

import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.connectToSite
import com.tony.albanese.mynews.controller.utilities.readDataFromConnection
import com.tony.albanese.mynews.controller.utilities.stringToUrl
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServerResponseIntegrationTest {

    @Test //This function tests the function that reads the response from the server with a valid REST api.
    fun testReaderResponse() {
        val url = stringToUrl("http://echo.jsontest.com/key/value/one/two")
        val connection = connectToSite(url!!)
        val response = readDataFromConnection(connection!!)
        val expectedResponse = "{\"one\": \"two\",\"key\": \"value\"}"
        Assert.assertEquals(expectedResponse.replace(" ", ""), response.replace(" ", ""))
    }
}