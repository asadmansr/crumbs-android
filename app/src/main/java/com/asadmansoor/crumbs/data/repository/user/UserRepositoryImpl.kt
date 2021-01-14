package com.asadmansoor.crumbs.data.repository.user

import com.asadmansoor.crumbs.data.db.entity.UserEntity
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSource

class UserRepositoryImpl(
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {

    override suspend fun loadUser(): UserEntity = localUserDataSource.loadUser()

    override suspend fun saveUser(name: String) = localUserDataSource.saveUser(name)
}
