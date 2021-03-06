package com.tony.albanese.mynews.controller.utilities

import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.CustomSearchModel.CustomSearch
import com.tony.albanese.mynews.model.MostPopularModel.MostPopular
import com.tony.albanese.mynews.model.TopStoriesModel.TopStories

/**
 * This function accepts a response string and creates article objects and places them in a ArrayList
 * and then returns the ArrayList
 */
fun generateArticleArray(resultType: Int, response: String): ArrayList<Article>{
    var list = ArrayList<Article>()
    val gson = Gson()
    lateinit var title:String
    lateinit var section: String
    lateinit var date: String
    lateinit var url: String
    lateinit var imageUrl: String

    when(resultType){
        1 -> {//Process MostPopular search results.
            val mostPopular = gson.fromJson(response, MostPopular::class.java)
            val resultsArray = mostPopular.results
            for (i in 0..(resultsArray.size - 1)) {
                var currentArticle = resultsArray[i]
                title = currentArticle.title
                section = currentArticle.section
                date = formatArticleDate(currentArticle.publishedDate)
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
                section = currentArticle.section ?: ""
                date = formatArticleDate(currentArticle.publishedDate)
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
                    var currentArticle = articleArray[i]
                    title = currentArticle.headline.printHeadline ?: ""
                    section = currentArticle.newsDesk ?: ""
                    date = formatArticleDate(currentArticle.pubDate)
                    url = currentArticle.webUrl
                    if (currentArticle.multimedia.size < 3) {
                        imageUrl = "Dummy URL"
                    }else{
                        imageUrl = "http://www.nytimes.com/" + currentArticle.multimedia[2].url
                    }
                    list.add(Article(title, section, date, url, imageUrl))
                }
            }
            return list
        }
        else -> return list
    }
}

/**
 * Accepts two array lists and updates the oldList with articles in the newList after checking
 * if the article exists in the old list (by title).
 */
fun updateArrayList(oldList: ArrayList<Article>, newList: ArrayList<Article>): ArrayList<Article> {
    //Load all of the titles in the old list into a list.
    var oldArticleTitleList = ArrayList<String>()

    for (article in oldList) {
        oldArticleTitleList.add(article.mTitle)
    }

    for (newArticle in newList) {
        /* Check the current article's title to see if it's in the list of old titles.
        If it is, then we know the article is not new. If it isn't, then it's a new article and has
        to be added to the old list.
         */
        if (!oldArticleTitleList.contains(newArticle.mTitle))
            oldList.add(0, newArticle)
    }
    return oldList
}

