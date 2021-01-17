package com.asadmansoor.crumbs.data.domain

data class CompletedStory(
    val storyId: String,
    val epicId: String,
    val title: String,
    val completed: Boolean,
    val createdAt: Long,
    val createdAtString: String,
    val lastUpdated: Long,
    val lastUpdatedString: String,
    val completedAt: Long,
    val completedAtString: String
)