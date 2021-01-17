package com.asadmansoor.crumbs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.AnalyticsEntity
import com.asadmansoor.crumbs.data.db.entity.SINGLE_USER_ID

@Dao
interface AnalyticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnalytics(analyticsEntity: AnalyticsEntity)

    @Query("select * from analytics_table where id = $SINGLE_USER_ID")
    suspend fun loadAnalytics(): AnalyticsEntity

    @Query("UPDATE analytics_table SET currentEpics = currentEpics + 1 WHERE id = $SINGLE_USER_ID")
    suspend fun incrementCurrentEpic()

    @Query("UPDATE analytics_table SET currentEpics = currentEpics - 1 WHERE id = $SINGLE_USER_ID")
    suspend fun decrementCurrentEpic()

    @Query("UPDATE analytics_table SET completedEpics = completedEpics + 1 WHERE id = $SINGLE_USER_ID")
    suspend fun incrementCompletedEpic()

    @Query("UPDATE analytics_table SET completedEpics = completedEpics - 1 WHERE id = $SINGLE_USER_ID")
    suspend fun decrementCompletedEpic()

    @Query("UPDATE analytics_table SET currentStories = currentStories + 1 WHERE id = $SINGLE_USER_ID")
    suspend fun incrementCurrentStory()

    @Query("UPDATE analytics_table SET currentStories = currentStories - 1 WHERE id = $SINGLE_USER_ID")
    suspend fun decrementCurrentStory()

    @Query("UPDATE analytics_table SET completedStories = completedStories + 1 WHERE id = $SINGLE_USER_ID")
    suspend fun incrementCompletedStory()

    @Query("UPDATE analytics_table SET completedStories = completedStories - 1 WHERE id = $SINGLE_USER_ID")
    suspend fun decrementCompletedStory()
}
