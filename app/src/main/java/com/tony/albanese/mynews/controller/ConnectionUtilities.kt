package com.tony.albanese.mynews.controller

import android.util.Log
import java.net.MalformedURLException
import java.net.URL

fun stringToUrl(urlString: String): URL? {
    val url: URL
    try{
        url = URL(urlString)
    } catch (exception: MalformedURLException){
        Log.e("From stringToUrl()", "Malformed URL.")
        return null
    }
    return url
}