package com.asadmansoor.crumbs.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CompletedEpicEntity

@Dao
interface CompletedEpicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentEpic: CompletedEpicEntity)

    @Query("select * from completed_epic_table")
    fun getCompletedEpic(): LiveData<List<CompletedEpicEntity>>
}
