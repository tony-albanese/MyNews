package com.tony.albanese.mynews.controller.utilities

import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.MostPopularModel.MostPopular

//The functions in this controller are responsible for processing the json response into an ArrayList of Article objects.

//This function accepts one parameter to indicate what type of search was performed and the json string itself.
fun generateArticleArray(searchType: Int, json: String): ArrayList<Article>{
    val gson = Gson()
    var array = ArrayList<Article>()
    //Let's start with MostPopular

    val mostPopularResult = gson.fromJson(json, MostPopular::class.java)

    val articleArray = mostPopularResult.results

    val title = articleArray[0].title
    val section = articleArray[0].section
    val publishedDate = articleArray[0].publishedDate
    val articleUrl = articleArray[0].url
    //val imageUrl = articleArray[0].multimedia[0].url

    array.add(Article(title, section, publishedDate, articleUrl, "gre"))
    return array
}
