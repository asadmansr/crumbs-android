package com.asadmansoor.crumbs.data.repository.current_epic

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSource
import com.asadmansoor.crumbs.internal.Result

class CurrentEpicRepositoryImpl(
    private val localCurrentEpicDataSource: LocalCurrentEpicDataSource
) : CurrentEpicRepository {

    override suspend fun getCurrentEpic(): LiveData<List<CurrentEpicEntity>> {
        return localCurrentEpicDataSource.getCurrentEpics()
    }

    override suspend fun saveEpic(name: String, description: String): LiveData<Result<Any>> {
        localCurrentEpicDataSource.createEpic(name, description)
        return localCurrentEpicDataSource.createEpicResult
    }
}
