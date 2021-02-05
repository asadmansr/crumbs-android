package com.asadmansoor.crumbs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asadmansoor.crumbs.data.db.dao.*
import com.asadmansoor.crumbs.data.db.entity.*

@Database(
    entities = [
        CompletedEpicEntity::class,
        CompletedStoryEntity::class,
        CurrentEpicEntity::class,
        CurrentStoryEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CrumbsDatabase : RoomDatabase() {

    abstract fun completedEpicDao(): CompletedEpicDao
    abstract fun completedStoryDao(): CompletedStoryDao
    abstract fun currentEpicDao(): CurrentEpicDao
    abstract fun currentStoryDao(): CurrentStoryDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: CrumbsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): CrumbsDatabase {
            val databaseName = "crumbs_database"

            return Room.databaseBuilder(
                context.applicationContext,
                CrumbsDatabase::class.java,
                databaseName
            ).build()
        }
    }
}
