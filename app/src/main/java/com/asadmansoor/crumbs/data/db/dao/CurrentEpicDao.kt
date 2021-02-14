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

    @Query("SELECT * FROM current_epic_table")
    fun getCurrentTasks(): List<CurrentEpicEntity>

    @Query("SELECT * FROM current_epic_table WHERE status = :status")
    fun getCurrentEpicsByFilter(status: Int): List<CurrentEpicEntity>

    @Query("SELECT * FROM current_epic_table WHERE epicId = :id")
    fun getEpicById(id: String): CurrentEpicEntity

    @Query("SELECT * FROM current_epic_table WHERE title = :name AND description = :description")
    fun getEpicByNameDescription(name: String, description: String): CurrentEpicEntity

    @Query("DELETE FROM current_epic_table WHERE epicId = :id")
    fun deleteEpic(id: String)

    @Query("UPDATE current_epic_table SET status = :status WHERE epicId = :id")
    fun updateStatus(id: String, status: Int)
}
