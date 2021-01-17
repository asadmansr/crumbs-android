package com.asadmansoor.crumbs.data.source.stories

import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.core.InputTransformer
import com.asadmansoor.crumbs.data.db.dao.CurrentStoryDao
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
import com.asadmansoor.crumbs.data.domain.Story

class LocalCurrentStoryDataSourceImpl(
    private val currentStoryDao: CurrentStoryDao,
    private val generateTimeParameter: GenerateTimeParameter,
    private val inputTransformer: InputTransformer
) : LocalCurrentStoryDataSource {

    override suspend fun addStory(title: String, epicId: String) {
        val currentStoryEntity = CurrentStoryEntity(
            storyId = generateTimeParameter.generateStoryId(),
            epicId = epicId,
            title = title,
            completed = false,
            createdAt = generateTimeParameter.generateTimestamp(),
            lastUpdated = generateTimeParameter.generateTimestamp()
        )
        currentStoryDao.insertStory(currentStoryEntity)
    }

    override suspend fun getStoriesByEpicId(id: String): List<Story> =
        currentStoryDao.getStoriesByEpic(id).map {
            Story(
                storyId = it.storyId,
                epicId = it.epicId,
                title = it.title,
                completed = it.completed,
                createdAt = it.createdAt,
                lastUpdated = it.lastUpdated,
                createdAtString = inputTransformer.convertDateToReadable(it.createdAt),
                lastUpdatedString = inputTransformer.convertDateToReadable(it.lastUpdated)
            )
        }

    override suspend fun deleteStory(currentStoryEntity: CurrentStoryEntity) = currentStoryDao.deleteStory(currentStoryEntity)

    override suspend fun updateStoryStatus(id: String, completed: Boolean) =
        currentStoryDao.updateStory(id, completed)
}
