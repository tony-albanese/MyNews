package com.tony.albanese.mynews.controller.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Converts a given string to URL
 */
fun stringToUrl(urlString: String): URL? {
    val url: URL
    try {
        url = URL(urlString)
    } catch (e: MalformedURLException) {
        return null
    }
    return url
}

/**
 * Accepts a URL and returns an HTTP connection.
 */
fun connectToSite(url: URL): HttpURLConnection? {
    try {
        val testConnection = url.openConnection() as HttpURLConnection
        return testConnection
    } catch (e: IOException) {
        return null
    }
}

/**
 * Reads data from the connection and returns the response as a string.
 */
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
        return ERROR
    }
}

/**
 * Function checks if the network connection on the device is available and returns a boolean to reflect that.
 */
fun networkIsAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInformation: NetworkInfo? = null
    activeNetworkInformation = connectivityManager.activeNetworkInfo
    if (activeNetworkInformation != null) {
        return !(activeNetworkInformation == null && activeNetworkInformation.isConnected)
    } else return false
}