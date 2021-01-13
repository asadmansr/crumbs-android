package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.SINGLE_USER_ID
import com.asadmansoor.crumbs.data.db.entity.StatsEntity

@Dao
interface StatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(statsEntity: StatsEntity)

    @Query("select * from stats_table where id = $SINGLE_USER_ID")
    suspend fun loadStats(): StatsEntity
}