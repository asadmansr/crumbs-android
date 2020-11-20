package com.asadmansoor.crumbs.data.repository.user

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.UserEntity

interface UserRepository {
    suspend fun getUser(): LiveData<UserEntity>
    fun saveUser(doneTutorial: Boolean)
}
