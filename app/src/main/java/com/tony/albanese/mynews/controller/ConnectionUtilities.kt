package com.tony.albanese.mynews.controller

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
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

fun connectToSite(url: URL): HttpURLConnection? {
    //val testConnection: HttpURLConnection
    try{
        val testConnection = url.openConnection() as HttpURLConnection
        return testConnection
    } catch (e: IOException){
        Log.e("From: connectToURl", e.toString())
        return null
    }

}

fun readDataFromConnection(connection: HttpURLConnection): String {
    val stringBuilder = StringBuilder()
    try {
        connection.connect()
    } catch (e: Exception) {
        Log.e("readDataFromConnection", e.toString())
        return "Error connecting."
    }

    var inStream = connection.getInputStream()
    var reader = BufferedReader(InputStreamReader(inStream))

    while (true) {
        var line = reader.readLine()
        if (line == null) break
        stringBuilder.append(line)
    }

    return stringBuilder.toString()
}