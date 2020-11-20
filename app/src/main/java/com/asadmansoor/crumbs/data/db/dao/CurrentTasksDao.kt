package com.asadmansoor.crumbs.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.CurrentTaskEntity

@Dao
interface CurrentTasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(currentTask: CurrentTaskEntity)

    @Query("select * from current_tasks_table")
    fun getCurrentTasks(): LiveData<List<CurrentTaskEntity>>
}