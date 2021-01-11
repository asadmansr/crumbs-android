package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_epic_table")
data class CompletedEpicEntity(
    val createdAt: Long,
    val lastUpdated: Long,
    val completedAt: Long,
    val key: Long,
    val title: String,
    val description: String,
    val status: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
