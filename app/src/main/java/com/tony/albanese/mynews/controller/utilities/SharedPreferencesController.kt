package com.tony.albanese.mynews.controller.utilities

import android.content.SharedPreferences
import com.google.gson.Gson
import com.tony.albanese.mynews.model.Article
import java.util.*

//Save the ArrayList to SharedPreferences after storing it as JSON data.
fun saveArrayListToSharedPreferences(preferences: SharedPreferences, key: String, array: ArrayList<Article>) {
    val gson = Gson()
    val stringedArrayList = gson.toJson(array)
    preferences.edit().putString(key, stringedArrayList).apply()
}