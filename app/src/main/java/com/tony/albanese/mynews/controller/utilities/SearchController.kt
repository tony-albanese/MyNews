package com.tony.albanese.mynews.controller.utilities

import android.content.Context
import android.net.Uri
import com.tony.albanese.mynews.R
import org.json.JSONObject

//Generates the search URL
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
            if (parameters != null) {
                val startDate = getSearchParametersFromJson("start_date", parameters) ?: ""
                val endDate = getSearchParametersFromJson("end_date", parameters) ?: ""
                val terms = getSearchParametersFromJson("search_terms", parameters) ?: ""
                val desks = getSearchParametersFromJson("news_desks", parameters) ?: ""
                builder.appendQueryParameter("q", terms)
                builder.appendQueryParameter("fq", desks)
                if (startDate.isNotBlank() && startDate.isNotBlank()) {
                    builder.appendQueryParameter("begin_date", startDate)
                }
                if (endDate.isNotBlank() && endDate.isNotEmpty()) {
                    builder.appendQueryParameter("end_date", endDate)
                }

                return builder.toString()
            } else return "No search parameters given."
        }
        else -> return "Invalid search type."
    }
}

//Generates the NewsDesk parameters needed for a custom search.
fun generateNewsDeskParameter(map: HashMap<Int, String>): String {
    val builder = StringBuilder()
    builder.append("news_desk:")
    builder.append("(")

    for ((position, value) in map.entries) {

        builder.append("\"$value\"")
        builder.append(" ")
    }

    builder.append(")")
    return builder.toString()


}
