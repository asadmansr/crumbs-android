package com.asadmansoor.crumbs.data.source.user

import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.db.dao.UserDao
import com.asadmansoor.crumbs.data.db.entity.UserEntity

class LocalUserDataSourceImpl(
    private val userDao: UserDao,
    private val generateTimeParameter: GenerateTimeParameter
) : LocalUserDataSource {

    override suspend fun loadUser(): UserEntity = userDao.loadUser()

    override suspend fun saveUser(name: String) {
        val userEntity = UserEntity(
            uid = generateTimeParameter.generateUid(name),
            name = name,
            accountCreated = generateTimeParameter.generateTimestamp(),
            tutorialCompleted = true
        )
        userDao.insertUser(userEntity)
    }
}
