package com.asadmansoor.crumbs.data.repository.stories

import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
import com.asadmansoor.crumbs.data.domain.Story

interface StoriesRepository {

    suspend fun addStory(title: String, epicId: String)

    suspend fun getStoriesByEpicId(id: String): List<Story>

    suspend fun deleteStory(currentStoryEntity: CurrentStoryEntity)

    suspend fun deleteAllStoriesOfEpic(id: String)

    suspend fun updateStoryStatus(id: String, completed: Boolean)

    suspend fun completeStories(list: List<Story>)
}
