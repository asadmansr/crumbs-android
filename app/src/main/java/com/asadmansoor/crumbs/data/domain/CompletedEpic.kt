package com.asadmansoor.crumbs.data.domain

data class CompletedEpic(
    val id: Int,
    val createdAt: Long,
    val createdAtString: String,
    val lastUpdated: Long,
    val lastUpdatedString: String,
    val key: Long,
    val title: String,
    val description: String,
    val status: Int,
    val statusString: String
)