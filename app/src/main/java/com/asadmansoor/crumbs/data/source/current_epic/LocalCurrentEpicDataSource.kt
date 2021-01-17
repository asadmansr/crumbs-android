package com.asadmansoor.crumbs.data.source.current_epic

import com.asadmansoor.crumbs.data.domain.CurrentEpic

interface LocalCurrentEpicDataSource {

    suspend fun getCurrentEpics(): List<CurrentEpic>

    suspend fun getCurrentEpicsByFilter(filter: Int): List<CurrentEpic>

    suspend fun createEpic(name: String, description: String)

    suspend fun getEpicById(id: String): CurrentEpic

    suspend fun getEpicByNameDescription(name: String, description: String): CurrentEpic

    suspend fun updateEpicStatus(id: String, status: Int)

    suspend fun deleteEpic(id: String)

    suspend fun completeEpic(id: String)
}
