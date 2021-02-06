package com.asadmansoor.crumbs.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabaseTest
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CurrentStoryDaoTest : CrumbsDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertStoryTest() = runBlockingTest {
        val primaryEntity = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my second story",
            completed = false
        )

        val secondaryEntity = CurrentStoryEntity(
            storyId = "stor-210206140000",
            epicId = "epic-210204160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is for another epic",
            completed = false
        )

        currentStoryDao.insertStory(primaryEntity)
        currentStoryDao.insertStory(secondaryEntity)

        val primaryStories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        val secondaryStories = currentStoryDao.getStoriesByEpic("epic-210204160000")

        Assert.assertEquals(1, primaryStories.size)
        Assert.assertEquals(1, secondaryStories.size)
        Assert.assertEquals(primaryEntity, primaryStories[0])
        Assert.assertEquals(secondaryEntity, secondaryStories[0])
    }

    @Test
    fun deleteStoryByIdTest() = runBlockingTest {
        val currentEntity = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my second story",
            completed = false
        )

        val deletedEntity = CurrentStoryEntity(
            storyId = "stor-210206140000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is for another epic",
            completed = false
        )

        currentStoryDao.insertStory(currentEntity)
        currentStoryDao.insertStory(deletedEntity)

        var primaryStories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(2, primaryStories.size)

        currentStoryDao.deleteStoryById("stor-210206140000")
        primaryStories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(1, primaryStories.size)
        Assert.assertEquals(currentEntity, primaryStories[0])
    }

    @Test
    fun deleteAllStoriesTest() = runBlockingTest {
        val currentEntity = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my second story",
            completed = false
        )

        val deletedEntity = CurrentStoryEntity(
            storyId = "stor-210206140000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is for another epic",
            completed = false
        )

        currentStoryDao.insertStory(currentEntity)
        currentStoryDao.insertStory(deletedEntity)

        var primaryStories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(2, primaryStories.size)

        currentStoryDao.deleteAllStoriesOfEpic("epic-210205160000")
        primaryStories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(0, primaryStories.size)
    }

    @Test
    fun updateStoryTest() = runBlockingTest {
        val primaryEntity = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my second story",
            completed = false
        )

        currentStoryDao.insertStory(primaryEntity)
        currentStoryDao.updateStory(primaryEntity.storyId, true)

        val primaryStories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(1, primaryStories.size)
        Assert.assertEquals(true, primaryStories[0].completed)
    }
}
