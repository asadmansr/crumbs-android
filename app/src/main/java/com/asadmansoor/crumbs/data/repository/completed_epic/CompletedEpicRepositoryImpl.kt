package com.asadmansoor.crumbs.data.repository.completed_epic

import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.source.completed_epic.LocalCompletedEpicDataSource

class CompletedEpicRepositoryImpl(
    private val localCompletedEpicDataSource: LocalCompletedEpicDataSource
) : CompletedEpicRepository {

    override suspend fun getCompletedEpics(): List<CompletedEpic> =
        localCompletedEpicDataSource.getCompletedEpics()

    override suspend fun getEpicById(id: String): CompletedEpic =
        localCompletedEpicDataSource.getEpicById(id)

    override suspend fun deleteCompletedEpic(id: String) =
        localCompletedEpicDataSource.deleteCompletedEpic(id)
}
