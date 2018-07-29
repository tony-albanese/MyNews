package com.tony.albanese.mynews

import com.tony.albanese.mynews.controller.utilities.stringToUrl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JvmConnectionTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun testValidUrl(){
        val goodUrl = "http://www.google.de"
        assertEquals(goodUrl, stringToUrl(goodUrl).toString())
    }

    @Test
    fun badUrlTest(){
        val badUrl = "htt www.foo" //A malformed url.
        val result = stringToUrl(badUrl)
        assertNull(result)
    }
}
