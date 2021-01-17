package com.asadmansoor.crumbs.data.source.stories

import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
import com.asadmansoor.crumbs.data.domain.Story

interface LocalCurrentStoryDataSource {

    suspend fun addStory(title: String, epicId: String)

    suspend fun getStoriesByEpicId(id: String): List<Story>

    suspend fun deleteStory(currentStoryEntity: CurrentStoryEntity)

    suspend fun updateStoryStatus(id: String, completed: Boolean)
}
