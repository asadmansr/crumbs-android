package com.asadmansoor.crumbs.data.db.dao

import androidx.room.*
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity

@Dao
interface CurrentStoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(currentStory: CurrentStoryEntity)

    @Query("select * from current_story_table where epicId = :epicId")
    fun getStoriesByEpic(epicId: String): List<CurrentStoryEntity>

    @Delete
    fun deleteStory(currentStoryEntity: CurrentStoryEntity)

    @Query("DELETE from current_story_table WHERE storyId = :id")
    fun deleteStoryById(id: String)

    @Query("DELETE from current_story_table WHERE epicId = :epicId")
    fun deleteAllStoriesOfEpic(epicId: String)

    @Query("UPDATE current_story_table SET completed = :status WHERE storyId = :id")
    fun updateStory(id: String, status: Boolean)
}
