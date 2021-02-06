package com.asadmansoor.crumbs.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabaseTest
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CurrentEpicDaoTest : CrumbsDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertEpicTest() = runBlockingTest {
        val currentEpic = CurrentEpicEntity(
            epicId = "epic-210206140000",
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 0
        )
        currentEpicDao.insert(currentEpic)

        val currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(1, currentEpicsList.size)
        assertEquals(currentEpic, currentEpicsList[0])
    }

    @Test
    fun getEpicByIdTest() = runBlockingTest {
        val epicId = "epic-210206150000"
        val currentEpic = CurrentEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 0
        )
        currentEpicDao.insert(currentEpic)

        val epic = currentEpicDao.getEpicById(epicId)
        assertEquals(currentEpic, epic)
    }

    @Test
    fun getEpicByNameAndDescriptionTest() = runBlockingTest {
        val epicId = "epic-210206160000"
        val currentEpic = CurrentEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic by searching by name and description",
            description = "This is my description for my epic!",
            status = 0
        )
        currentEpicDao.insert(currentEpic)

        val epic =
            currentEpicDao.getEpicByNameDescription(currentEpic.title, currentEpic.description)
        assertEquals(currentEpic, epic)
    }

    @Test
    fun deleteEpicTest() = runBlockingTest {
        val epicId = "epic-210206141000"
        val currentEpic = CurrentEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic to be deleted",
            description = "This is my description for my epic.",
            status = 0
        )
        currentEpicDao.insert(currentEpic)
        var currentEpicsList = currentEpicDao.getCurrentTasks()

        assertEquals(1, currentEpicsList.size)
        assertEquals(currentEpic, currentEpicsList[0])

        currentEpicDao.deleteEpic(epicId)
        currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(0, currentEpicsList.size)
    }

    @Test
    fun updateEpicTest() = runBlockingTest {
        val epicId = "epic-210206142000"
        val currentEpic = CurrentEpicEntity(
            epicId = epicId,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 0
        )
        currentEpicDao.insert(currentEpic)
        currentEpicDao.updateStatus(epicId, 1)

        var currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(1, currentEpicsList.size)
        assertEquals(1, currentEpicsList[0].status)

        currentEpicDao.updateStatus(epicId, 2)
        currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(2, currentEpicsList[0].status)

        currentEpicDao.updateStatus(epicId, 3)
        currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(3, currentEpicsList[0].status)
    }

    @Test
    fun updateEpicFilterTest() = runBlockingTest {
        val epicIdNotStarted = "epic-210206143000"
        val epicIdInProgress = "epic-210206144000"
        val epicIdPaused = "epic-210206145000"

        val notStartedEpic = CurrentEpicEntity(
            epicId = epicIdNotStarted,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 0
        )

        val inProgressEpic = CurrentEpicEntity(
            epicId = epicIdInProgress,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 0
        )

        val pausedEpic = CurrentEpicEntity(
            epicId = epicIdPaused,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 0
        )

        currentEpicDao.insert(notStartedEpic)
        currentEpicDao.insert(inProgressEpic)
        currentEpicDao.insert(pausedEpic)

        currentEpicDao.updateStatus(epicIdPaused, 1)
        currentEpicDao.updateStatus(epicIdInProgress, 2)

        val allEpics = currentEpicDao.getCurrentTasks()
        val notStartedEpics = currentEpicDao.getCurrentEpicsByFilter(0)
        val pausedEpics = currentEpicDao.getCurrentEpicsByFilter(1)
        val inProgressEpics = currentEpicDao.getCurrentEpicsByFilter(2)

        assertEquals(3, allEpics.size)
        assertEquals(1, notStartedEpics.size)
        assertEquals(1, pausedEpics.size)
        assertEquals(1, inProgressEpics.size)
    }
}
