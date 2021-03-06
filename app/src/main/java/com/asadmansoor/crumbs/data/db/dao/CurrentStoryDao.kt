package com.asadmansoor.crumbs.data.db.dao

import androidx.room.*
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity

@Dao
interface CurrentStoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(currentStory: CurrentStoryEntity)

    @Query("SELECT * FROM current_story_table WHERE epicId = :epicId")
    fun getStoriesByEpic(epicId: String): List<CurrentStoryEntity>

    @Delete
    fun deleteStory(currentStoryEntity: CurrentStoryEntity)

    @Query("DELETE FROM current_story_table WHERE storyId = :id")
    fun deleteStoryById(id: String)

    @Query("DELETE FROM current_story_table WHERE epicId = :epicId")
    fun deleteAllStoriesOfEpic(epicId: String)

    @Query("UPDATE current_story_table SET completed = :status WHERE storyId = :id")
    fun updateStory(id: String, status: Boolean)
}
