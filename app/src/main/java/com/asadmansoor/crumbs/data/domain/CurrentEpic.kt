package com.asadmansoor.crumbs.data.domain

data class CurrentEpic(
    val epicId: String,
    val createdAt: Long,
    val createdAtString: String,
    val lastUpdated: Long,
    val lastUpdatedString: String,
    val title: String,
    val description: String,
    val status: Int,
    val statusString: String
)
