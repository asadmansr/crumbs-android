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
    fun insertEpic_isAdded() = runBlockingTest {
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
        assertEquals(currentEpic, currentEpicsList.first())
    }

    @Test
    fun getEpicById_isRetrievedCorrectly() = runBlockingTest {
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
    fun getEpicByNameAndDescription_isRetrievedCorrectly() = runBlockingTest {
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
    fun deleteEpic_isDeleted() = runBlockingTest {
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

        currentEpicDao.deleteEpic(epicId)
        currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(0, currentEpicsList.size)
    }

    @Test
    fun updateEpicByStatus_isUpdated() = runBlockingTest {
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
        assertEquals(1, currentEpicsList.first().status)

        currentEpicDao.updateStatus(epicId, 2)
        currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(2, currentEpicsList.first().status)

        currentEpicDao.updateStatus(epicId, 3)
        currentEpicsList = currentEpicDao.getCurrentTasks()
        assertEquals(3, currentEpicsList.first().status)
    }

    @Test
    fun getEpicsByStatus_isFilteredCorrectly() = runBlockingTest {
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
            status = 2
        )

        val pausedEpic = CurrentEpicEntity(
            epicId = epicIdPaused,
            createdAt = 0L,
            lastUpdated = 0L,
            title = "This is my first epic",
            description = "This is my description for my epic.",
            status = 1
        )

        currentEpicDao.insert(notStartedEpic)
        currentEpicDao.insert(inProgressEpic)
        currentEpicDao.insert(pausedEpic)

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
