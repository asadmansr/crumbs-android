package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "current_tasks_table")
data class CurrentTaskEntity(
    val task: String,
    val completed: Boolean,
    val createdAt: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
