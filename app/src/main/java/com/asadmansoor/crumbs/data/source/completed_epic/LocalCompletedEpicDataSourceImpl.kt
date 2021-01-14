package com.asadmansoor.crumbs.data.source.completed_epic

import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.core.InputTransformer
import com.asadmansoor.crumbs.data.db.dao.CompletedEpicDao
import com.asadmansoor.crumbs.data.db.entity.CompletedEpicEntity
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.domain.CurrentEpic

class LocalCompletedEpicDataSourceImpl(
    private val completedEpicDao: CompletedEpicDao,
    private val generateTimeParameter: GenerateTimeParameter,
    private val inputTransformer: InputTransformer
) : LocalCompletedEpicDataSource {

    override suspend fun getCompletedEpics(): List<CompletedEpic> =
        completedEpicDao.getCompletedEpic().map {
            CompletedEpic(
                epicId = it.epicId,
                createdAt = it.createdAt,
                createdAtString = inputTransformer.convertDateToReadable(it.createdAt),
                lastUpdated = it.lastUpdated,
                lastUpdatedString = inputTransformer.convertDateToReadable(it.lastUpdated),
                completedAt = it.completedAt,
                completedAtString = inputTransformer.convertDateToReadable(it.completedAt),
                title = it.title,
                description = it.description,
                status = it.status,
                statusString = inputTransformer.convertStatusToString(it.status)
            )
        }

    override suspend fun getEpicById(id: String): CompletedEpic {
        val epic: CompletedEpicEntity? = completedEpicDao.getEpicById(id)
        val completedEpic: CompletedEpic
        if (epic != null) {
            completedEpic = CompletedEpic(
                epicId = epic.epicId,
                createdAt = epic.createdAt,
                createdAtString = inputTransformer.convertDateToReadable(epic.createdAt),
                lastUpdated = epic.lastUpdated,
                lastUpdatedString = inputTransformer.convertDateToReadable(epic.lastUpdated),
                completedAt = epic.completedAt,
                completedAtString = inputTransformer.convertDateToReadable(epic.completedAt),
                title = epic.title,
                description = epic.description,
                status = epic.status,
                statusString = inputTransformer.convertStatusToString(epic.status)
            )
        } else {
            completedEpic = CompletedEpic(
                epicId = "",
                createdAt = -1,
                createdAtString = "",
                lastUpdated = -1,
                lastUpdatedString = "",
                completedAt = -1,
                completedAtString = "",
                title = "",
                description = "",
                status = -1,
                statusString = ""
            )
        }
        return completedEpic
    }

    override suspend fun completeEpic(epic: CurrentEpic) {
        val epicEntity = CompletedEpicEntity(
            epicId = "",
            createdAt = epic.createdAt,
            lastUpdated = epic.lastUpdated,
            completedAt = generateTimeParameter.generateTimestamp(),
            title = epic.title,
            description = epic.description,
            status = 3
        )
        completedEpicDao.insert(epicEntity)
    }

    override suspend fun deleteCompletedEpic(id: String) =
        completedEpicDao.deleteCompletedEpic(id = id)
}
