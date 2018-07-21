package com.tony.albanese.mynews

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.stringToUrl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectionUnitTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tony.albanese.mynews", appContext.packageName)
    }

    @Test
    fun validUrlTest(){
        val urlString = "http://www.google.de"
        assertEquals(urlString, stringToUrl(urlString).toString())
    }

    @Test
    fun badUrlTest(){
        val badUrl = "htt www.goo com"
        assertEquals(null, stringToUrl(badUrl))
    }

}
