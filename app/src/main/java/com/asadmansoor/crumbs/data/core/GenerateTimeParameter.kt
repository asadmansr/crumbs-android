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
        return "$name-$dateString"
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
}
