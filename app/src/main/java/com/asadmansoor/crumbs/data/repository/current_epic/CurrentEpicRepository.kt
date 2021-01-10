package com.asadmansoor.crumbs.data.repository.current_epic

import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.domain.CurrentEpic

interface CurrentEpicRepository {

    suspend fun getCurrentEpics(): List<CurrentEpic>

    suspend fun createEpic(name: String, description: String)

    suspend fun getCreatedEpic(): CurrentEpicEntity

    suspend fun getEpicById(id: Int): CurrentEpic

    suspend fun updateEpicStatus(id: Int, status: Int)

    suspend fun deleteEpic(id: Int)
}
