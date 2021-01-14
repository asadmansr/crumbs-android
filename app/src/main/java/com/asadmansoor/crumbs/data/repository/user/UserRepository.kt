package com.asadmansoor.crumbs.data.repository.user

import com.asadmansoor.crumbs.data.db.entity.UserEntity

interface UserRepository {

    suspend fun loadUser(): UserEntity

    suspend fun saveUser(name: String)
}
