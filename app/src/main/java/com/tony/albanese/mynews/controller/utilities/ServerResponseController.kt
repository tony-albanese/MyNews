package com.tony.albanese.mynews.controller.utilities

import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.CustomSearchModel.CustomSearch
import com.tony.albanese.mynews.model.MostPopularModel.MostPopular
import com.tony.albanese.mynews.model.TopStoriesModel.TopStories


//This is the function that populates an ArrayList of Articles after parsing the JSON response.
//The model used depends on the type of result passed. (Not all JSON responses have the same structure.
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

//This function updates the ArrayList and adds new articles.
fun updateArrayList(oldList: ArrayList<Article>, newList: ArrayList<Article>): ArrayList<Article> {

    for (article in newList) {
        if (!oldList.contains(article)) {
            oldList.add(0, article)
        }
    }
    return oldList
}