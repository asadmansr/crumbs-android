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
    fun multipleStoriesForMultipleEpics_isAddedCorrectly() = runBlockingTest {
        val firstStory = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my first story in epic A",
            completed = false
        )

        val secondStory = CurrentStoryEntity(
            storyId = "stor-210206140000",
            epicId = "epic-210204160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my first story in epic B",
            completed = false
        )

        currentStoryDao.insertStory(firstStory)
        currentStoryDao.insertStory(secondStory)

        val storiesOfFirstEpic = currentStoryDao.getStoriesByEpic("epic-210205160000")
        val storiesOfSecondEpic = currentStoryDao.getStoriesByEpic("epic-210204160000")

        Assert.assertEquals(1, storiesOfFirstEpic.size)
        Assert.assertEquals(firstStory, storiesOfFirstEpic.first())

        Assert.assertEquals(1, storiesOfSecondEpic.size)
        Assert.assertEquals(secondStory, storiesOfSecondEpic.first())
    }

    @Test
    fun deleteSingleStoryInAEpic_isDeletedCorrectly() = runBlockingTest {
        val firstStory = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Title for my second story",
            completed = false
        )

        val storyToBeDeleted = CurrentStoryEntity(
            storyId = "stor-210206140000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is for another epic",
            completed = false
        )

        currentStoryDao.insertStory(firstStory)
        currentStoryDao.insertStory(storyToBeDeleted)

        var stories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(2, stories.size)

        currentStoryDao.deleteStoryById("stor-210206140000")

        stories = currentStoryDao.getStoriesByEpic("epic-210205160000")
        Assert.assertEquals(1, stories.size)
        Assert.assertEquals(firstStory, stories.first())
    }

    @Test
    fun deleteAllStoriesInAEpic_isDeleted() = runBlockingTest {
        val epicId = "epic-210205160000"

        val firstStory = CurrentStoryEntity(
            storyId = "stor-210206150000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "First story",
            completed = false
        )

        val secondStory = CurrentStoryEntity(
            storyId = "stor-210206140000",
            epicId = "epic-210205160000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "Second story",
            completed = false
        )

        currentStoryDao.insertStory(firstStory)
        currentStoryDao.insertStory(secondStory)

        var stories = currentStoryDao.getStoriesByEpic(epicId)
        Assert.assertEquals(2, stories.size)

        currentStoryDao.deleteAllStoriesOfEpic(epicId)
        stories = currentStoryDao.getStoriesByEpic(epicId)
        Assert.assertEquals(0, stories.size)
    }

    @Test
    fun completedAStory_isUpdated() = runBlockingTest {
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
        Assert.assertEquals(true, primaryStories.first().completed)
    }
}
