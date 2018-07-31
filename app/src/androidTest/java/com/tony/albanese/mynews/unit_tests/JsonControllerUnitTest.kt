package com.tony.albanese.mynews.unit_tests

import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.generateArticleArrayList
import com.tony.albanese.mynews.controller.utilities.generateServerResponse
import com.tony.albanese.mynews.model.Article
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

//This class is for testing the JsonController.


//TODO: Implement test for createSearchParametersJson()

//TODO: Implement tests for parsing Json Object when the function is implemented in the controller.

@RunWith(AndroidJUnit4::class)
class JsonControllerUnitTest {

    val testJson = ""
    @Test
    fun testGenerateArticleArrayList(){
        val response = generateServerResponse(testJson)
        val testList: ArrayList<Article> = generateArticleArrayList(response)
        assertEquals(1, testList.size)
    }

}
