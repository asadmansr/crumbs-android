package com.asadmansoor.crumbs.data.source.user

import com.asadmansoor.crumbs.data.db.entity.UserEntity

interface LocalUserDataSource {

    suspend fun loadUser(): UserEntity

    suspend fun saveUser(doneTutorial: Boolean)
}
