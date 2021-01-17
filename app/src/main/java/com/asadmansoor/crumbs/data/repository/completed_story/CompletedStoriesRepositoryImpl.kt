package com.asadmansoor.crumbs.data.repository.completed_story

import com.asadmansoor.crumbs.data.domain.CompletedStory
import com.asadmansoor.crumbs.data.source.completed_stories.LocalCompletedStoryDataSource

class CompletedStoriesRepositoryImpl(
    private val completedStoryDataSource: LocalCompletedStoryDataSource
) : CompletedStoriesRepository {

    override suspend fun getCompletedStories(epicId: String): List<CompletedStory> =
        completedStoryDataSource.getCompletedStories(epicId)

    override suspend fun deleteAllStoriesOfEpic(id: String) =
        completedStoryDataSource.deleteAllStoriesOfEpic(id)
}
