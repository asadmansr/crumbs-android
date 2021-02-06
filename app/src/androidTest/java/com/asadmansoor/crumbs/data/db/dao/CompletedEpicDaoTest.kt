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
    fun insertEpicTest() = runBlockingTest {
        val currentEpic = CompletedEpicEntity(
            epicId = "epic-210206140000",
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 3
        )
        completedEpicDao.insert(currentEpic)

        val currentEpicsList = completedEpicDao.getCompletedEpic()
        Assert.assertEquals(1, currentEpicsList.size)
        Assert.assertEquals(currentEpic, currentEpicsList[0])
    }

    @Test
    fun getEpicByIdTest() = runBlockingTest {
        val epicId = "epic-210206150000"
        val currentEpic = CompletedEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 3
        )
        completedEpicDao.insert(currentEpic)

        val epic = completedEpicDao.getEpicById(epicId)
        Assert.assertEquals(currentEpic, epic)
    }


    @Test
    fun deleteEpicTest() = runBlockingTest {
        val epicId = "epic-210206150000"
        val currentEpic = CompletedEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            completedAt = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 3
        )
        completedEpicDao.insert(currentEpic)

        var currentEpicsList = completedEpicDao.getCompletedEpic()
        Assert.assertEquals(1, currentEpicsList.size)
        Assert.assertEquals(currentEpic, currentEpicsList[0])

        completedEpicDao.deleteCompletedEpic(epicId)
        currentEpicsList = completedEpicDao.getCompletedEpic()
        Assert.assertEquals(0, currentEpicsList.size)
    }
}
