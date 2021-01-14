package com.asadmansoor.crumbs.data.repository.current_epic

import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.source.completed_epic.LocalCompletedEpicDataSource
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSource

class CurrentEpicRepositoryImpl(
    private val localCurrentEpicDataSource: LocalCurrentEpicDataSource,
    private val localCompletedEpicDataSource: LocalCompletedEpicDataSource
) : CurrentEpicRepository {

    override suspend fun getCurrentEpics(): List<CurrentEpic> =
        localCurrentEpicDataSource.getCurrentEpics()

    override suspend fun getCurrentEpicsByFilter(filter: Int): List<CurrentEpic> =
        localCurrentEpicDataSource.getCurrentEpicsByFilter(filter = filter)

    override suspend fun createEpic(name: String, description: String) =
        localCurrentEpicDataSource.createEpic(name = name, description = description)

    override suspend fun getEpicById(id: String): CurrentEpic =
        localCurrentEpicDataSource.getEpicById(id = id)

    override suspend fun updateEpicStatus(id: String, status: Int) =
        localCurrentEpicDataSource.updateEpicStatus(id = id, status = status)

    override suspend fun deleteEpic(id: String) = localCurrentEpicDataSource.deleteEpic(id = id)

    override suspend fun completeEpic(id: String, epic: CurrentEpic) {
        localCompletedEpicDataSource.completeEpic(epic = epic)
        localCurrentEpicDataSource.deleteEpic(id = id)
    }
}
