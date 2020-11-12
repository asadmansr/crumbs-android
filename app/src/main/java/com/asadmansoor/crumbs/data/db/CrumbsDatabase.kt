package com.asadmansoor.crumbs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asadmansoor.crumbs.data.db.dao.UserDao
import com.asadmansoor.crumbs.data.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class CrumbsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: CrumbsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CrumbsDatabase::class.java,
                "crumbs_database"
            ).build()
    }
}
