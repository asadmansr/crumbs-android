package com.asadmansoor.crumbs.data.domain

data class Story(
    val id: Int,
    val storyId: Int,
    val epicId: Int,
    val text: String,
    val completed: Boolean,
    val createdAt: Long,
    val createdAtString: String,
    val lastUpdated: Long,
    val lastUpdatedString: String
)
