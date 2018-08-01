package com.tony.albanese.mynews.controller.utilities

import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.MostPopularModel.MostPopular
import com.tony.albanese.mynews.model.TopStoriesModel.TopStories

fun generateArticleArray(resultType: Int, response: String): ArrayList<Article>{
    var list = ArrayList<Article>()
    lateinit var title:String
    lateinit var section: String
    lateinit var date: String
    lateinit var url: String
    lateinit var imageUrl: String

    when(resultType){
        1 -> {
            val gson = Gson()
            val mostPopular = gson.fromJson(response, MostPopular::class.java)
            val resultsArray = mostPopular.results
            for (i in 0..(resultsArray.size - 1)) {
                var currentArticle = resultsArray[i]
                title = currentArticle.title
                section = currentArticle.section
                date = currentArticle.publishedDate
                url = currentArticle.url
                imageUrl = currentArticle.media[0].mediaMetadata[0].url
                list.add(Article(title, section, date, url, imageUrl))
            }
            return list
        }

        2->{
            val gson = Gson()
            val topStories = gson.fromJson(response, TopStories::class.java)
            val resultsArray = topStories.results
            list.clear()
            for(i in 0.. (resultsArray.size -1 )){
                var currentArticle = resultsArray[i]
                title = currentArticle.title
                section = currentArticle.section
                date = currentArticle.publishedDate
                url = currentArticle.url

                if(currentArticle.multimedia.isEmpty()){
                    imageUrl = "Dummy url"
                } else{
                    imageUrl = currentArticle.multimedia[0].url
                }
                list.add(Article(title,section,date,url,"Dummy url"))
            }
            return list
        }
        else -> return list
    }









}