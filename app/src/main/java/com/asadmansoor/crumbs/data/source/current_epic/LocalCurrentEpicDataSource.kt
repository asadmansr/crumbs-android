package com.asadmansoor.crumbs.data.source.current_epic

import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.domain.CurrentEpic

interface LocalCurrentEpicDataSource {

    suspend fun getCurrentEpics(): List<CurrentEpic>

    suspend fun getCurrentEpicsByFilter(filter: Int): List<CurrentEpic>

    suspend fun createEpic(name: String, description: String)

    suspend fun getCreatedEpic(): CurrentEpicEntity

    suspend fun getEpicById(id: Int): CurrentEpic

    suspend fun updateEpicStatus(id: Int, status: Int)

    suspend fun deleteEpic(id: Int)

    suspend fun completeEpic(id: Int)
}
