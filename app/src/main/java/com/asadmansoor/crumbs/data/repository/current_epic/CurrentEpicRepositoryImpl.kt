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
        localCurrentEpicDataSource.createEpic(name, description)

    override suspend fun getCreatedEpic(): CurrentEpicEntity =
        localCurrentEpicDataSource.getCreatedEpic()
}
