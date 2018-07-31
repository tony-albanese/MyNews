package com.tony.albanese.mynews.controller.utilities

import android.util.Log
import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import com.tony.albanese.mynews.model.ServerResponse
import org.json.JSONException
import org.json.JSONObject

/*This is one function that gets called when the user clicks the "Search" button.
It will accept the search terms, dates, and news desk settings and packages them as a JSON Object.
This JSON Object will get passed to the generate search URL.
 */
//TODO: write test cases for this function by itself.
fun createSearchParametersJson(terms: String, startDate: String = "", endDate: String = "", desks: String = ""): JSONObject {
    var json = JSONObject()
    try {
        json.put("search_terms", terms)
        json.put("start_date", startDate)
        json.put("end_date", endDate)
        json.put("news_desks", desks)
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return json
}

//This function accepts a string key and a JSONObject and returns a string value corresponding to the key.
fun getSearchParametersFromJson(key: String, json: JSONObject): String? {
    try {
        return json.getString(key)

    } catch (e: JSONException) {
        Log.e("getJsonValue()", "Error parsing JSON.")
    }
    return null
}

//This function accepts a string from a json response and returns a ServerResponse object.
//TODO: Update unit tests for this function.
fun generateServerResponse(jsonResponse: String): ServerResponse {
    val gson = Gson()
    val serverResponse = gson.fromJson(jsonResponse, ServerResponse::class.java)
    return serverResponse
}

//This function accepts a ServerResponse and will return an array list of Articles.
fun generateArticleArrayList(serverResponse: ServerResponse): ArrayList<Article>{

    //TODO: Make sure to loop over all of the values in the array.
    var list = ArrayList<Article>()
    var documentArray = serverResponse.response.docs
    var currentDocument= documentArray[0]
    val article = Article(currentDocument.headline.main, currentDocument.pubDate, currentDocument.snippet, currentDocument.newsDesk, currentDocument.webUrl, currentDocument.multimedia.get(0).url)
    list.add(article)

    return list
}


