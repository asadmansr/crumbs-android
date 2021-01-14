package com.asadmansoor.crumbs.data.core

import java.text.SimpleDateFormat
import java.util.*

class InputTransformer {

    private val dateFormatter = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    fun convertDateToReadable(dateRepresentation: Long): String {
        val date = SimpleDateFormat(
            "yyMMddhhmmss",
            Locale.getDefault()
        ).parse(dateRepresentation.toString())
        return dateFormatter.format(date!!)
    }

    fun convertStatusToString(status: Int): String {
        return when (status) {
            0 -> "Not Started"
            1 -> "Paused"
            2 -> "In Progress"
            3 -> "Done"
            else -> "Unknown"
        }
    }
}
