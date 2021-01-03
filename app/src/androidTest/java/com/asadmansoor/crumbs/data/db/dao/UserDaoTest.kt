package com.asadmansoor.crumbs.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabaseTest
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class UserDaoTest : CrumbsDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertNewUserTest() = runBlocking {
        val user = UserEntity(doneTutorial = false)
        userDao.insertUser(user)
        assertEquals(userDao.loadUser().doneTutorial, false)
    }

    @Test
    fun updateUserTutorialTest() = runBlocking {
        val newUser = UserEntity(doneTutorial = false)
        val tutorialCompletedUser = UserEntity(doneTutorial = true)
        userDao.insertUser(newUser)
        userDao.insertUser(tutorialCompletedUser)
        assertEquals(userDao.loadUser().doneTutorial, true)
    }

    @Test
    fun getUserTest() = runBlocking {
        val user = UserEntity(doneTutorial = false)
        userDao.insertUser(user)
        assertEquals(userDao.loadUser().doneTutorial, false)
    }
}
