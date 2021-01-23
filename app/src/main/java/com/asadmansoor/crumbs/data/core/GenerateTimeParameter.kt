package com.asadmansoor.crumbs.data.core

import java.text.SimpleDateFormat
import java.util.*

class GenerateTimeParameter {

    private val dateFormatter = SimpleDateFormat("yyMMddhhmmss", Locale.getDefault())
    private lateinit var date: Date
    private lateinit var dateString: String

    fun generateUid(name: String): String {
        date = Date()
        dateString = dateFormatter.format(date)
        return "${formatName(name)}-$dateString"
    }

    fun generateTimestamp(): Long {
        date = Date()
        dateString = dateFormatter.format(date)
        return dateString.toLong()
    }

    fun generateEpicId(): String {
        date = Date()
        dateString = dateFormatter.format(date)
        return "epic-$dateString"
    }

    fun generateStoryId(): String {
        date = Date()
        dateString = dateFormatter.format(date)
        return "stor-$dateString"
    }

    private fun formatName(name: String): String {
        if (name.length > 8) {
            return name.substring(0,8).toLowerCase(Locale.ROOT)
        }

        return name.toLowerCase(Locale.ROOT)
    }
}
