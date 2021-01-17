package com.asadmansoor.crumbs.data.domain

data class Story(
    val storyId: String,
    val epicId: String,
    val title: String,
    val completed: Boolean,
    val createdAt: Long,
    val createdAtString: String,
    val lastUpdated: Long,
    val lastUpdatedString: String
)
