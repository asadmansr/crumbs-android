package com.asadmansoor.crumbs.data.source.current_epic

import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity

interface LocalCurrentEpicDataSource {

    suspend fun getCurrentEpics(): List<CurrentEpicEntity>

    suspend fun createEpic(name: String, description: String)

    suspend fun getCreatedEpic(): CurrentEpicEntity
}
