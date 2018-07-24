package com.tony.albanese.mynews.controller

import android.content.Context
import android.net.Uri
import com.tony.albanese.mynews.R
import org.json.JSONObject

fun generateSearchUrl(c: Context, searchType: Int, searchTerms: JSONObject = JSONObject()): String {
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
            //TODO: Implement the method that extracts the information from JSON.
            return "Method 4 called."
        }
        else -> return "Invalid search type."
    }
}


