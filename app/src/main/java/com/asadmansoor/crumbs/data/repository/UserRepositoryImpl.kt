package com.asadmansoor.crumbs.data.repository

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.dao.UserDao
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(): LiveData<UserEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUser()
        }
    }

    override fun saveUser(doneTutorial: Boolean) {
        val userEntity = UserEntity(doneTutorial = doneTutorial)
        GlobalScope.launch(Dispatchers.IO) {
            userDao.upsert(userEntity = userEntity)
        }
    }
}
