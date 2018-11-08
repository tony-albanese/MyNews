package com.tony.albanese.mynews.controller.utilities

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tony.albanese.mynews.model.Article

fun saveArrayListToSharedPreferences(preferences: SharedPreferences, key: String, array: ArrayList<Article>) {
    val gson = Gson()
    val stringedArrayList = gson.toJson(array)
    preferences.edit().putString(key, stringedArrayList).commit()
}

fun loadArrayListFromSharedPreferences(preferences: SharedPreferences, key: String): ArrayList<Article> {
    var list = ArrayList<Article>()
    val gson = Gson() //Create a Gson object instance.
    val articleListData = preferences.getString(key, "none")
    val type = object : TypeToken<ArrayList<Article>>() {
    }.type //Declare the type of object that should be restored.
    if (articleListData != "none") {
        list = gson.fromJson(articleListData, type) //Restore the object from Json
    }

    return list
}

fun mapToString(map: HashMap<Int, String>): String {
    val gson = Gson()
    val mapString = gson.toJson(map)
    return mapString
}

fun getMapFromPrefereces(prefs: SharedPreferences, key: String): HashMap<Int, String> {
    val gson = Gson()
    var map = HashMap<Int, String>()
    val mapString = prefs.getString(key, "NONE")
    if (mapString.equals("NONE")) {
        return map
    } else {
        val type = object : TypeToken<HashMap<Int, String>>() {}.type
        val map = gson.fromJson(mapString, type) as HashMap<Int, String>
        return map
    }
}