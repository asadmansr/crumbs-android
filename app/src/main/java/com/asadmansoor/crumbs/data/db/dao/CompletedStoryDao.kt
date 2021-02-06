package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CompletedStoryEntity
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity

@Dao
interface CompletedStoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(completedStory: CompletedStoryEntity)

    @Query("select * from completed_story_table where epicId = :epicId")
    fun getStoriesByEpic(epicId: String): List<CompletedStoryEntity>

    @Query("DELETE from completed_story_table WHERE epicId = :epicId")
    fun deleteAllStoriesOfEpic(epicId: String)
}
