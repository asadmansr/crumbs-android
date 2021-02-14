package com.asadmansoor.crumbs.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabaseTest
import com.asadmansoor.crumbs.data.db.entity.CompletedEpicEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CompletedEpicDaoTest : CrumbsDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addCompletedEpic_isAdded() = runBlockingTest {
        val completedEpic = CompletedEpicEntity(
            epicId = "epic-210206140000",
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 3
        )
        completedEpicDao.insert(completedEpic)

        val currentEpicsList = completedEpicDao.getCompletedEpic()
        Assert.assertEquals(1, currentEpicsList.size)
        Assert.assertEquals(completedEpic, currentEpicsList.first())
    }

    @Test
    fun getCompletedEpicById_isRetrievedCorrectly() = runBlockingTest {
        val epicId = "epic-210206150000"
        val completedEpic = CompletedEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 3
        )
        completedEpicDao.insert(completedEpic)

        val epic = completedEpicDao.getEpicById(epicId)
        Assert.assertEquals(completedEpic, epic)
    }

    @Test
    fun deleteCompletedEpic_isDeleted() = runBlockingTest {
        val epicId = "epic-210206150000"
        val completedEpic = CompletedEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 3
        )
        completedEpicDao.insert(completedEpic)

        var currentEpicsList = completedEpicDao.getCompletedEpic()
        Assert.assertEquals(1, currentEpicsList.size)
        Assert.assertEquals(completedEpic, currentEpicsList.first())

        completedEpicDao.deleteCompletedEpic(epicId)
        currentEpicsList = completedEpicDao.getCompletedEpic()
        Assert.assertEquals(0, currentEpicsList.size)
    }
}
