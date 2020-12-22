package com.asadmansoor.crumbs.data.db.dao

import androidx.lifecycle.LiveData
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
    fun getCurrentTasks(): LiveData<List<CurrentEpicEntity>>
}
