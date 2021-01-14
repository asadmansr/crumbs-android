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

    @Query("select * from current_epic_table where epicId = :id")
    fun getEpicById(id: String): CurrentEpicEntity

    @Query("DELETE FROM current_epic_table WHERE epicId = :id")
    fun deleteEpic(id: String)

    @Query("UPDATE current_epic_table SET status = :status WHERE epicId = :id")
    fun updateStatus(id: String, status: Int)
}
