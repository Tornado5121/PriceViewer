package com.zhadko.priceviewer.domain.mappers.date

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toProperTime(): String {
    return try {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("MMMM dd HH:mm", Locale.getDefault())
        val date = inputFormatter.parse(this)
        if (date != null) {
            outputFormatter.format(date)
        } else {
            "Invalid Date"
        }
    } catch (e: ParseException) {
        "Invalid Date"
    }
}
