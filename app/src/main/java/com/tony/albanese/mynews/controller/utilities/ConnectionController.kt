package com.tony.albanese.mynews.controller.utilities

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/*
The functions in this file are resposible for connecting the site and fetching data from it.
stringToUrl() -- converts string to URL object
connectToSite() -- actually connects to the site.
readDataFromConnection() -- reads the data and returns it as a string.
 */


//Converts a string to a URL object.
fun stringToUrl(urlString: String): URL? {
    val url: URL
    try {
        url = URL(urlString)
    } catch (e: MalformedURLException) {
        Log.e("From stringToUrl()", "Malformed URL.")
        return null
    }
    return url
}

//Accepts a URL and returns an HttpURLConnection.
fun connectToSite(url: URL): HttpURLConnection? {
    //val testConnection: HttpURLConnection
    try {
        val testConnection = url.openConnection() as HttpURLConnection
        return testConnection
    } catch (e: IOException) {
        Log.e("From: connectToURl", e.toString())
        return null
    }

}

//Reads the data from connection and builds a JSON response string and returns it.
fun readDataFromConnection(connection: HttpURLConnection): String {
    val stringBuilder = StringBuilder()
    try {
        connection.connect()

        var inStream = connection.inputStream
        var reader = BufferedReader(InputStreamReader(inStream))

        while (true) {
            var line = reader.readLine()
            if (line == null) break
            stringBuilder.append(line)
        }

        return stringBuilder.toString()
    } catch (e: Exception) {
        Log.e("readDataFromConnection", e.toString())
        return "Error connecting."
    }

}
