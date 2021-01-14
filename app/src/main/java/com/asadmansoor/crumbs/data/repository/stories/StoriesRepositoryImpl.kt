package com.asadmansoor.crumbs.data.repository.stories

import com.asadmansoor.crumbs.data.domain.Story

class StoriesRepositoryImpl() : StoriesRepository {
    override suspend fun getStoriesByEpicId(id: String): List<Story> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteStory(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStoryStatus(id: String) {
        TODO("Not yet implemented")
    }
}
