package com.asadmansoor.crumbs.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.dao.UserDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class CrumbsDatabaseTest {

    protected lateinit var database: CrumbsDatabase
    protected lateinit var userDao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CrumbsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        userDao = database.userDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }
}