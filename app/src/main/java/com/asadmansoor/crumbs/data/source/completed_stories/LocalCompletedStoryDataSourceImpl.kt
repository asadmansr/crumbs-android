package com.asadmansoor.crumbs.data.source.completed_stories

import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.core.InputTransformer
import com.asadmansoor.crumbs.data.db.dao.CompletedStoryDao
import com.asadmansoor.crumbs.data.db.entity.CompletedStoryEntity
import com.asadmansoor.crumbs.data.domain.CompletedStory
import com.asadmansoor.crumbs.data.domain.Story

class LocalCompletedStoryDataSourceImpl(
    private val completedStoryDao: CompletedStoryDao,
    private val generateTimeParameter: GenerateTimeParameter,
    private val inputTransformer: InputTransformer
) : LocalCompletedStoryDataSource {

    override suspend fun addToCompleteTable(story: Story) {
        val completedStoryEntity = CompletedStoryEntity(
            storyId = story.storyId,
            epicId = story.epicId,
            createdAt = story.createdAt,
            lastUpdated = story.lastUpdated,
            completedAt = generateTimeParameter.generateTimestamp(),
            title = story.title,
            completed = true
        )
        completedStoryDao.insertStory(completedStoryEntity)
    }

    override suspend fun getCompletedStories(epicId: String): List<CompletedStory> =
        completedStoryDao.getStoriesByEpic(epicId).map {
            CompletedStory(
                storyId = it.storyId,
                epicId = it.epicId,
                createdAt = it.createdAt,
                lastUpdated = it.lastUpdated,
                completedAt = it.completedAt,
                title = it.title,
                completed = it.completed,
                completedAtString = inputTransformer.convertDateToReadable(it.completedAt),
                lastUpdatedString = inputTransformer.convertDateToReadable(it.lastUpdated),
                createdAtString = inputTransformer.convertDateToReadable(it.createdAt)
            )
        }

    override suspend fun deleteAllStoriesOfEpic(id: String) =
        completedStoryDao.deleteAllStoriesOfEpic(id)
}
