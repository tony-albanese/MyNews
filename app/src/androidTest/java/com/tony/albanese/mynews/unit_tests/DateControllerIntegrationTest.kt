package com.tony.albanese.mynews.unit_tests

import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.formatArticleDate
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class DateControllerIntegrationTest {

    val SIMPLE_TEST_DATE_STRING = "2018-08-10"
    val COMPLEX_TEST_DATE_STRING = "2018-08-14T05:30:12-04:00"
    @Test
    fun simpleDateConversionTest() {
        val expectedDate = "10/08/2018"
        val testDate = formatArticleDate(SIMPLE_TEST_DATE_STRING)
        assertEquals(expectedDate, testDate)
    }

    @Test
    fun complexDateConversionTest() {
        val formattedDate = formatArticleDate(COMPLEX_TEST_DATE_STRING)
        assertEquals("14/08/2018", formattedDate)
    }


}