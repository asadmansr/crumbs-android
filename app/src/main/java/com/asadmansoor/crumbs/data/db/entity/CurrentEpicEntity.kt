package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_epic_table")
data class CurrentEpicEntity(
    val createdAt: Long,
    val lastUpdated: Long,
    val title: String,
    val description: String,
    val status: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
