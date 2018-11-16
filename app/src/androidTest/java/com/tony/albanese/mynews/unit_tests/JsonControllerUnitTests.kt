package com.tony.albanese.mynews.unit_tests

import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.createSearchParametersJson
import com.tony.albanese.mynews.controller.utilities.getSearchParametersFromJson
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JsonControllerUnitTests {

    @Test
    fun createSearchParametersFromJsonTestValidInput() {
        val termsString = "One Two Three"
        val startDate = "10/10/10"
        val endDate = "11/11/11"
        val newsDesks = "Editorial Science Politics"
        val jsonParamters = createSearchParametersJson(termsString, startDate, endDate, newsDesks)

        val isInvalid = with(jsonParamters) {
            isNull("search_terms")
            isNull("start_date")
            isNull("end_date")
            isNull("news_desks")
        }
        Assert.assertEquals(false, isInvalid)
    }

    @Test
    fun getSearchParametersFromJsonTest() {
        val termsString = "One Two Three"
        val startDate = "10/10/10"
        val endDate = "11/11/11"
        val newsDesks = "Editorial Science Politics"
        val jsonParameters = createSearchParametersJson(termsString, startDate, endDate, newsDesks)

        val expectedTermsString = getSearchParametersFromJson("search_terms", jsonParameters)
        val expectedStartDate = getSearchParametersFromJson("start_date", jsonParameters)
        val expectedEndDate = getSearchParametersFromJson("end_date", jsonParameters)
        val expectedNewsDesks = getSearchParametersFromJson("news_desks", jsonParameters)

        val expectedString = termsString + startDate + endDate + newsDesks
        val testString = expectedTermsString + expectedStartDate + expectedEndDate + expectedNewsDesks

        Assert.assertEquals(testString, expectedString)
    }
}