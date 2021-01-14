package com.asadmansoor.crumbs.data.repository.stories

import com.asadmansoor.crumbs.data.domain.Story

interface StoriesRepository {

    suspend fun getStoriesByEpicId(id: String): List<Story>

    suspend fun deleteStory(id: String)

    suspend fun updateStoryStatus(id: String)
}