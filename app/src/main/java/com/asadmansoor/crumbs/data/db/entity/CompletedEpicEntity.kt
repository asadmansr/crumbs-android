package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_epic_table")
data class CompletedEpicEntity(
    @PrimaryKey(autoGenerate = false)
    val epicId: String,
    val createdAt: Long,
    val lastUpdated: Long,
    val completedAt: Long,
    val title: String,
    val description: String,
    val status: Int
)
