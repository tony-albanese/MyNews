package com.tony.albanese.mynews.controller.utilities

import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.MostPopularModel.MostPopular

fun generateArticleArray(resultType: Int, response: String): ArrayList<Article>{
    var list = ArrayList<Article>()
    val gson = Gson()

    when(resultType){
        1 -> {
            val mostPopular = gson.fromJson(response, MostPopular::class.java)
            val resultsArray = mostPopular.results
            for (i in 0..(resultsArray.size - 1)) {
                var currentArticle = resultsArray[i]
                val title = currentArticle.title
                val section = currentArticle.section
                val date = currentArticle.publishedDate
                val url = currentArticle.url
                val imageUrl = currentArticle.media[0].mediaMetadata[0].url
                list.add(Article(title, section, date, url, imageUrl))
            }
            return list
        }

        2->{

            return list
        }
        else -> return list
    }









}