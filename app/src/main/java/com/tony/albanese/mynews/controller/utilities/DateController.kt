package com.tony.albanese.mynews.controller.utilities

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * This function accepts a string and formats it for display in an Article object.
 */
fun formatArticleDate(date: String): String {
    val initialFormat = SimpleDateFormat("yyy-MM-dd")
    val dateFormat = SimpleDateFormat("dd/MM/yyy") //Date should be in 02/02/1982 format.

    try {
        val date = initialFormat.parse(date)
        return dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        return ERROR
    }

}

/**
 * Accepts a Date object and a format string and converts it into a format required by the NYT API.
 */
fun convertDate(date: Date, formatString: String = "dd/MM/yyy"): String {
    val dateFormat = SimpleDateFormat(formatString) //Date should be in 02/02/1982 format.
    return dateFormat.format(date)
}

