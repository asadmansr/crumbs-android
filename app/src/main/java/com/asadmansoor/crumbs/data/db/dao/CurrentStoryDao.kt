package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity

@Dao
interface CurrentStoryDao {

    @Query("select * from current_story_table where epicId = :epicId")
    fun getStoriesByEpic(epicId: String): List<CurrentStoryEntity>

}
