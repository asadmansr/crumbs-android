package com.asadmansoor.crumbs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val SINGLE_USER_ID = 0

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = SINGLE_USER_ID,
    val uid: String,
    val name: String,
    val accountCreated: Long,
    val tutorialCompleted: Boolean
)
