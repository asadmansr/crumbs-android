package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_story_table")
data class CompletedStoryEntity(
    @PrimaryKey(autoGenerate = false)
    val storyId: String,
    val epicId: String,
    val createdAt: Long,
    val lastUpdated: Long,
    val completedAt: Long,
    val title: String,
    val completed: Boolean
)
