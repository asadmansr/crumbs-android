package com.asadmansoor.crumbs.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabaseTest
import com.asadmansoor.crumbs.data.db.entity.CompletedStoryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CompletedStoryDaoTest : CrumbsDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addMultipleStoriesToCompletedEpic_isAdded() = runBlockingTest {
        val epicId = "epic-210204160000"

        val firstStory = CompletedStoryEntity(
            storyId = "stor-210206150000",
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "Title for my first story",
            completed = false
        )

        val secondStory = CompletedStoryEntity(
            storyId = "stor-210206140000",
            epicId = epicId,
            completedAt = 0L,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for second story",
            completed = false
        )

        completedStoryDao.insertStory(firstStory)
        completedStoryDao.insertStory(secondStory)

        val stories = completedStoryDao.getStoriesByEpic(epicId)

        Assert.assertEquals(2, stories.size)
        Assert.assertEquals(firstStory, stories[0])
        Assert.assertEquals(secondStory, stories[1])
    }

    @Test
    fun deleteAllStoriesInACompletedEpic_isDeleted() = runBlockingTest {
        val epicId = "epic-210204160000"

        val firstStory = CompletedStoryEntity(
            storyId = "stor-210206150000",
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "Title for my first story",
            completed = false
        )

        val secondStory = CompletedStoryEntity(
            storyId = "stor-210206140000",
            epicId = epicId,
            completedAt = 0L,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for second story",
            completed = false
        )

        completedStoryDao.insertStory(firstStory)
        completedStoryDao.insertStory(secondStory)

        var stories = completedStoryDao.getStoriesByEpic(epicId)
        Assert.assertEquals(2, stories.size)

        completedStoryDao.deleteAllStoriesOfEpic(epicId)
        stories = completedStoryDao.getStoriesByEpic(epicId)
        Assert.assertEquals(0, stories.size)
    }
}
