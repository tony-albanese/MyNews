package com.tony.albanese.mynews.controller

import android.content.Context
import android.net.Uri
import com.tony.albanese.mynews.R
import org.json.JSONException
import org.json.JSONObject

fun generateSearchUrl(c: Context, searchType: Int, parameters: JSONObject = JSONObject()): String {
    val SCHEME = "https"
    val AUTHORITY = c.getString(R.string.AUTHORITY)
    val KEY = c.getString(R.string.API_KEY)
    val builder = Uri.Builder()
    builder.scheme(SCHEME)
    builder.authority(AUTHORITY)

    when (searchType) {
        1 -> {
            val path = c.getString(R.string.MOST_READ_PATH)
            builder.appendEncodedPath(path)
            builder.appendQueryParameter("api-key", KEY)
            return builder.toString()
        }
        2 -> {
            val path = c.getString(R.string.TOP_STORIES_PATH)
            builder.appendEncodedPath(path)
            builder.appendQueryParameter("api-key", KEY)
            return builder.toString()
        }
        3 -> {
            val path = c.getString(R.string.TOP_SCIENCE_PATH)
            builder.appendEncodedPath(path)
            builder.appendQueryParameter("api-key", KEY)
            return builder.toString()
        }
        4 -> {
            val path = c.getString(R.string.ARTICLE_SEARCH_PATH)
            builder.appendEncodedPath(path)
            builder.appendQueryParameter("api-key", KEY)

            //TODO: Implement safety checks to make sure values exist and for defaults.
            val startDate = parameters.getString("start_date")
            val endDate = parameters.getString("end_date")
            val terms = parameters.getString("search_terms")
            val desks = parameters.getString("news_desks")
            builder.appendQueryParameter("q", terms)
            builder.appendQueryParameter("fq", desks)
            builder.appendQueryParameter("begin_date", startDate)
            builder.appendQueryParameter("end_date", endDate)
            return builder.toString()
        }
        else -> return "Invalid search type."
    }
}

/*This is one function that gets called when the user clicks the "Search" button.
It will accept the search terms, dates, and news desk settings and packages them as a JSON Object.
This JSON Object will get passed to the generate search URL.
 */
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