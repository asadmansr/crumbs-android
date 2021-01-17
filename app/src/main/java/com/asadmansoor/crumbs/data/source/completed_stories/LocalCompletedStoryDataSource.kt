package com.asadmansoor.crumbs.data.source.completed_stories

import com.asadmansoor.crumbs.data.domain.CompletedStory
import com.asadmansoor.crumbs.data.domain.Story

interface LocalCompletedStoryDataSource {

    suspend fun addToCompleteTable(story: Story)

    suspend fun getCompletedStories(epicId: String): List<CompletedStory>

    suspend fun deleteAllStoriesOfEpic(id: String)

}
