package com.asadmansoor.crumbs.data.source.current_epic

import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.core.InputTransformer
import com.asadmansoor.crumbs.data.db.dao.CurrentEpicDao
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.domain.CurrentEpic

class LocalCurrentEpicDataSourceImpl(
    private val currentEpicDao: CurrentEpicDao,
    private val generateTimeParameter: GenerateTimeParameter,
    private val inputTransformer: InputTransformer
) : LocalCurrentEpicDataSource {

    override suspend fun getCurrentEpics(): List<CurrentEpic> =
        currentEpicDao.getCurrentTasks().map {
            CurrentEpic(
                epicId = it.epicId,
                createdAt = it.createdAt,
                createdAtString = inputTransformer.convertDateToReadable(it.createdAt),
                lastUpdated = it.lastUpdated,
                lastUpdatedString = inputTransformer.convertDateToReadable(it.lastUpdated),
                title = it.title,
                description = it.description,
                status = it.status,
                statusString = inputTransformer.convertStatusToString(it.status)
            )
        }

    override suspend fun getCurrentEpicsByFilter(filter: Int): List<CurrentEpic> =
        currentEpicDao.getCurrentEpicsByFilter(filter).map {
            CurrentEpic(
                epicId = it.epicId,
                createdAt = it.createdAt,
                createdAtString = inputTransformer.convertDateToReadable(it.createdAt),
                lastUpdated = it.lastUpdated,
                lastUpdatedString = inputTransformer.convertDateToReadable(it.lastUpdated),
                title = it.title,
                description = it.description,
                status = it.status,
                statusString = inputTransformer.convertStatusToString(it.status)
            )
        }

    override suspend fun createEpic(name: String, description: String) {
        val epicEntity = CurrentEpicEntity(
            epicId = generateTimeParameter.generateEpicId(),
            createdAt = generateTimeParameter.generateTimestamp(),
            lastUpdated = generateTimeParameter.generateTimestamp(),
            title = name,
            description = description,
            status = 0
        )
        currentEpicDao.insert(epicEntity)
    }

    override suspend fun getEpicById(id: String): CurrentEpic {
        val epic: CurrentEpicEntity? = currentEpicDao.getEpicById(id)
        val currentEpic: CurrentEpic
        if (epic != null) {
            currentEpic = CurrentEpic(
                epicId = epic.epicId,
                createdAt = epic.createdAt,
                createdAtString = inputTransformer.convertDateToReadable(epic.createdAt),
                lastUpdated = epic.lastUpdated,
                lastUpdatedString = inputTransformer.convertDateToReadable(epic.lastUpdated),
                title = epic.title,
                description = epic.description,
                status = epic.status,
                statusString = inputTransformer.convertStatusToString(epic.status)
            )
        } else {
            currentEpic = CurrentEpic(
                epicId = "",
                createdAt = -1,
                createdAtString = "",
                lastUpdated = -1,
                lastUpdatedString = "",
                title = "",
                description = "",
                status = -1,
                statusString = ""
            )
        }

        return currentEpic
    }

    override suspend fun updateEpicStatus(id: String, status: Int) =
        currentEpicDao.updateStatus(id = id, status = status)

    override suspend fun deleteEpic(id: String) = currentEpicDao.deleteEpic(id = id)

    override suspend fun completeEpic(id: String) = currentEpicDao.deleteEpic(id = id)
}
