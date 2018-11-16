package com.tony.albanese.mynews.controller.utilities

import org.json.JSONException
import org.json.JSONObject

/**
 * Creates JSON object from input search paramters.
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

/**
 * Function accepts key and JSON object and returns the string that corresponds to that key. Returns null if the key is invalid.
 */
fun getSearchParametersFromJson(key: String, json: JSONObject): String? {
    try {
        return json.getString(key)
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return null
}

