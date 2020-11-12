package com.asadmansoor.crumbs.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asadmansoor.crumbs.data.db.entity.SINGLE_USER_ID
import com.asadmansoor.crumbs.data.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(userEntity: UserEntity)

    @Query("select * from user_table where id = $SINGLE_USER_ID")
    fun getUser(): LiveData<UserEntity>
}
