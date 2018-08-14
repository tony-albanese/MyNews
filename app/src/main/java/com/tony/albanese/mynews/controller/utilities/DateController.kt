package com.tony.albanese.mynews.controller.utilities

import java.text.ParseException
import java.text.SimpleDateFormat

fun formatDate(date: String): String {
    val initialFormat = SimpleDateFormat("yyy-MM-dd")
    val dateFormat = SimpleDateFormat("dd/MM/yyy") //Date should be in 02/02/1982 format.

    try {
        val date = initialFormat.parse(date)
        val formattedDate = dateFormat.format(date)
        return formattedDate
    } catch (e: ParseException) {
        e.printStackTrace()
        return "No date"
    }
    //return "No Date"
}