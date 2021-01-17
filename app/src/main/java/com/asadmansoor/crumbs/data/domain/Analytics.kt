package com.asadmansoor.crumbs.data.domain

data class Analytics(
    val id: Int,
    val uid: String,
    val completedEpics: Int,
    val completedStories: Int,
    val currentEpics: Int,
    val currentStories: Int
)
