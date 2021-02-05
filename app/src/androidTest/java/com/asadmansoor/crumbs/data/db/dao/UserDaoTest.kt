package com.asadmansoor.crumbs.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabaseTest
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
open class UserDaoTest : CrumbsDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertNewUserTest() = runBlockingTest {
        val user = UserEntity(
            uid = "u0",
            name = "Test user",
            accountCreated = 0L,
            tutorialCompleted = false
        )
        userDao.insertUser(user)

        val savedUser = userDao.loadUser()
        assertEquals(user, savedUser)
    }

    @Test
    fun updateUserTest() = runBlockingTest {
        val user = UserEntity(
            uid = "u100",
            name = "New user",
            accountCreated = 0L,
            tutorialCompleted = false
        )

        val updatedUser = UserEntity(
            uid = "u100",
            name = "New user",
            accountCreated = 0L,
            tutorialCompleted = true
        )

        userDao.insertUser(user)
        assertEquals(user, userDao.loadUser())

        userDao.insertUser(updatedUser)
        assertEquals(updatedUser, userDao.loadUser())
    }
}
