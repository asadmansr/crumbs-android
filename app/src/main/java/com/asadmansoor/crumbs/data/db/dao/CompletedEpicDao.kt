package com.asadmansoor.crumbs.data.db.dao

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
    fun getCompletedEpic(): List<CompletedEpicEntity>

    @Query("select * from completed_epic_table where id = :id")
    fun getEpicById(id: Int): CompletedEpicEntity

    @Query("DELETE FROM completed_epic_table WHERE id = :id")
    fun deleteCompletedEpic(id: Int)
}
