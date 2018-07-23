package com.tony.albanese.mynews.controller

import android.content.Context
import android.net.Uri
import com.tony.albanese.mynews.R
import org.json.JSONArray

fun generateSearchUrl(c: Context, searchType: Int, searchTerms: JSONArray = JSONArray()): String {
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
        else -> return "Invalid search type."
    }
}