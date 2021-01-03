package com.asadmansoor.crumbs.data.source.user

import com.asadmansoor.crumbs.data.db.dao.UserDao
import com.asadmansoor.crumbs.data.db.entity.UserEntity

class LocalUserDataSourceImpl(
    private val userDao: UserDao
) : LocalUserDataSource {

    override suspend fun loadUser(): UserEntity = userDao.loadUser()

    override suspend fun saveUser(doneTutorial: Boolean) {
        val userEntity = UserEntity(doneTutorial = doneTutorial)
        userDao.insertUser(userEntity)
    }
}
