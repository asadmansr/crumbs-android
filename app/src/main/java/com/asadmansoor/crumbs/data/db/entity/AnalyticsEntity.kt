package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "analytics_table")
data class AnalyticsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = SINGLE_USER_ID,
    val uid: String,
    val completedEpics: Int,
    val completedStories: Int,
    val currentEpics: Int,
    val currentStories: Int
)
