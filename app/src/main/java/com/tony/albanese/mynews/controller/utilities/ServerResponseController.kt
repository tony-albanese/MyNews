package com.tony.albanese.mynews.controller.utilities

import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.CustomSearchModel.CustomSearch
import com.tony.albanese.mynews.model.MostPopularModel.MostPopular
import com.tony.albanese.mynews.model.TopStoriesModel.TopStories

fun generateArticleArray(resultType: Int, response: String): ArrayList<Article>{
    var list = ArrayList<Article>()
    val gson = Gson()
    lateinit var title:String
    lateinit var section: String
    lateinit var date: String
    lateinit var url: String
    lateinit var imageUrl: String

    //TODO: Make sure to include checks if arrays are empty before trying to fetch elements from them.
    when(resultType){
        1 -> {//Process MostPopular search results.

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

        2->{//Process TopStories search results.
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
                list.add(Article(title, section, date, url, imageUrl))
            }
            return list
        }

        3->{//Process CustomSearch results.
            val customSearch = gson.fromJson(response, CustomSearch::class.java)
            val articleArray = customSearch.response.docs

            if(articleArray.isEmpty()){
                list.clear()
                return list
            }else{
                for(i in 0.. (articleArray.size -1 )){
                    var currentArticle = articleArray[0]
                    title = currentArticle.headline.printHeadline
                    section = currentArticle.sectionName
                    date = currentArticle.pubDate
                    url = currentArticle.webUrl
                    if(currentArticle.multimedia.isEmpty()){
                        imageUrl = "Dummy URL"
                    }else{
                        imageUrl = currentArticle.multimedia[0].url
                    }
                    list.add(Article(title, section, date, url, imageUrl))
                }
            }
            return list
        }
        else -> return list
    }









}