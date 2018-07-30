package com.tony.albanese.mynews.controller.utilities

import android.content.Context
import android.net.Uri
import com.tony.albanese.mynews.R
import org.json.JSONObject

//Generates
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
            //TODO: Move this functionality to JsonController
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

