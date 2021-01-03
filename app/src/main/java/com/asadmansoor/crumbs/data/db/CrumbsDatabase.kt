package com.asadmansoor.crumbs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asadmansoor.crumbs.data.db.dao.CompletedEpicDao
import com.asadmansoor.crumbs.data.db.dao.CurrentEpicDao
import com.asadmansoor.crumbs.data.db.dao.UserDao
import com.asadmansoor.crumbs.data.db.entity.CompletedEpicEntity
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.db.entity.UserEntity

@Database(
    entities = [UserEntity::class, CurrentEpicEntity::class, CompletedEpicEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CrumbsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun currentEpicDao(): CurrentEpicDao
    abstract fun completedEpicDao(): CompletedEpicDao

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
