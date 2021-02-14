package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CompletedStoryEntity

@Dao
interface CompletedStoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(completedStory: CompletedStoryEntity)

    @Query("SELECT * FROM completed_story_table WHERE epicId = :epicId")
    fun getStoriesByEpic(epicId: String): List<CompletedStoryEntity>

    @Query("DELETE FROM completed_story_table WHERE epicId = :epicId")
    fun deleteAllStoriesOfEpic(epicId: String)
}
