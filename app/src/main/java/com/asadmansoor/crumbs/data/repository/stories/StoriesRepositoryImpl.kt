package com.asadmansoor.crumbs.data.repository.stories

import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
import com.asadmansoor.crumbs.data.domain.Story
import com.asadmansoor.crumbs.data.source.completed_stories.LocalCompletedStoryDataSource
import com.asadmansoor.crumbs.data.source.stories.LocalCurrentStoryDataSource

class StoriesRepositoryImpl(
    private val localCurrentStoryDataSource: LocalCurrentStoryDataSource,
    private val localCompletedStoryDataSource: LocalCompletedStoryDataSource
) : StoriesRepository {

    override suspend fun addStory(title: String, epicId: String) =
        localCurrentStoryDataSource.addStory(title, epicId)

    override suspend fun getStoriesByEpicId(id: String): List<Story> =
        localCurrentStoryDataSource.getStoriesByEpicId(id)

    override suspend fun deleteStory(currentStoryEntity: CurrentStoryEntity) =
        localCurrentStoryDataSource.deleteStory(currentStoryEntity)

    override suspend fun deleteAllStoriesOfEpic(id: String) =
        localCurrentStoryDataSource.deleteAllStoriesOfEpic(id)

    override suspend fun updateStoryStatus(id: String, completed: Boolean) =
        localCurrentStoryDataSource.updateStoryStatus(id, completed)

    override suspend fun completeStories(list: List<Story>) {
        for (i in list) {
            localCompletedStoryDataSource.addToCompleteTable(i)
            localCurrentStoryDataSource.deleteStoryById(i.storyId)
        }
    }
}
