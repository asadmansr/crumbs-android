package com.asadmansoor.crumbs.data.repository.current_epic

import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSource

class CurrentEpicRepositoryImpl(
    private val localCurrentEpicDataSource: LocalCurrentEpicDataSource
) : CurrentEpicRepository {

    override suspend fun getCurrentEpics(): List<CurrentEpic> =
        localCurrentEpicDataSource.getCurrentEpics()

    override suspend fun createEpic(name: String, description: String) =
        localCurrentEpicDataSource.createEpic(name = name, description = description)

    override suspend fun getCreatedEpic(): CurrentEpicEntity =
        localCurrentEpicDataSource.getCreatedEpic()

    override suspend fun getEpicById(id: Int): CurrentEpic =
        localCurrentEpicDataSource.getEpicById(id = id)

    override suspend fun updateEpicStatus(id: Int, status: Int) =
        localCurrentEpicDataSource.updateEpicStatus(id = id, status = status)

    override suspend fun deleteEpic(id: Int) = localCurrentEpicDataSource.deleteEpic(id = id)
}
