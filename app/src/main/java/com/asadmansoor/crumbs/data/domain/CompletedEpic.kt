package com.asadmansoor.crumbs.data.domain

data class CompletedEpic(
    val epicId: String,
    val createdAt: Long,
    val createdAtString: String,
    val lastUpdated: Long,
    val lastUpdatedString: String,
    val completedAt: Long,
    val completedAtString: String,
    val title: String,
    val description: String,
    val status: Int,
    val statusString: String
)