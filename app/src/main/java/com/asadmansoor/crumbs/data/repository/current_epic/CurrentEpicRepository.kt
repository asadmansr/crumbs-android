package com.asadmansoor.crumbs.data.repository.current_epic

import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity

interface CurrentEpicRepository {

    suspend fun getCurrentEpics(): List<CurrentEpicEntity>

    suspend fun createEpic(name: String, description: String)

    suspend fun getCreatedEpic(): CurrentEpicEntity
}
