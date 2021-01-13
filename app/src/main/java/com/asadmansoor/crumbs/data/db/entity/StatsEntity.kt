package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats_table")
data class StatsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = SINGLE_USER_ID,
    val completedEpic: Int,
    val completedStories: Int,
    val currentEpic: Int,
    val currentStories: Int
)
