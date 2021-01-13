package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity

@Dao
interface CurrentEpicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentEpic: CurrentEpicEntity)

    @Query("select * from current_epic_table")
    fun getCurrentTasks(): List<CurrentEpicEntity>

    @Query("select * from current_epic_table where status = :status")
    fun getCurrentEpicsByFilter(status: Int): List<CurrentEpicEntity>

    @Query("select * from current_epic_table where key = :key")
    fun getCreatedEpic(key: Long): CurrentEpicEntity

    @Query("select * from current_epic_table where id = :id")
    fun getEpicById(id: Int): CurrentEpicEntity

    @Query("DELETE FROM current_epic_table WHERE id = :id")
    fun deleteEpic(id: Int)

    @Query("UPDATE current_epic_table SET status = :status WHERE id = :id")
    fun updateStatus(id: Int, status: Int)
}
