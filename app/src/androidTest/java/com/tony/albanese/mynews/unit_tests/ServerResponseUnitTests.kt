package com.tony.albanese.mynews.unit_tests

import android.support.test.runner.AndroidJUnit4
import com.tony.albanese.mynews.controller.utilities.updateArrayList
import com.tony.albanese.mynews.model.Article
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServerResponseUnitTests {
    var list1 = ArrayList<Article>()
    var list2 = ArrayList<Article>()
    var list3 = ArrayList<Article>()

    val article1 = Article("One", "One", "One", "One", "One")
    val article2 = Article("Two", "Two", "Two", "Two", "Two")
    val article3 = Article("Three", "Three", "Three", "Three", "Three")
    @Before
    fun init() {
        list1.add(article1)
        list1.add(article2)

        list2.add(article1)
        list2.add(article3)

        list3.add(article1)
        list3.add(article2)
        list3.add(article3)
    }

    //TODO: Implement these tests.
    /*
    Need a test to process the server response for each of the search responses.

    //Save a prepared response as a string.
    //Convert it to an array.
    //Verify the length of the array with an assert
     */


    //Test to check the
    @Test
    fun testUpdateArrayListSize() {
        val testList = updateArrayList(list1, list2)
        assertEquals(list3.size, testList.size)
    }

    @Test
    fun testUpdateArrayListMembers() {
        val testList = updateArrayList(list1, list2)
        val testArticle = testList[0]
        val expectedArticle = list2[1]
        assertEquals(expectedArticle.mTitle, testArticle.mTitle)
    }
}