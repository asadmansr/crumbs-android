package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_story_table")
data class CurrentStoryEntity(
    @PrimaryKey(autoGenerate = false)
    val storyId: String,
    val epicId: String,
    val createdAt: Long,
    val lastUpdated: Long,
    val title: String,
    val completed: Boolean
)
