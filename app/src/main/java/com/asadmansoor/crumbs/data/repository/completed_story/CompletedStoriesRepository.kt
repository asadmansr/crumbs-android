package com.asadmansoor.crumbs.data.repository.completed_story

import com.asadmansoor.crumbs.data.domain.CompletedStory

interface CompletedStoriesRepository {

    suspend fun getCompletedStories(epicId: String): List<CompletedStory>

    suspend fun deleteAllStoriesOfEpic(id: String)
}
