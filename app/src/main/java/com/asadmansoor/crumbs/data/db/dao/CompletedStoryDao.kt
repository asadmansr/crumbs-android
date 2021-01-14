package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CompletedStoryEntity

@Dao
interface CompletedStoryDao {

    @Query("select * from completed_story_table where epicId = :epicId")
    fun getStoriesByEpic(epicId: String): List<CompletedStoryEntity>

}
