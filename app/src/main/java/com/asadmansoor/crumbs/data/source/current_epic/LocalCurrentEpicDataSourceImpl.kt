package com.asadmansoor.crumbs.data.source.current_epic

import com.asadmansoor.crumbs.data.db.dao.CurrentEpicDao
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import java.text.SimpleDateFormat
import java.util.*

class LocalCurrentEpicDataSourceImpl(
    private val currentEpicDao: CurrentEpicDao
) : LocalCurrentEpicDataSource {

    override suspend fun getCurrentEpics(): List<CurrentEpicEntity> =
        currentEpicDao.getCurrentTasks()

    override suspend fun createEpic(name: String, description: String) {
        val epicEntity = CurrentEpicEntity(
            createdAt = 0L,
            lastUpdated = 0L,
            key = generateKey(),
            title = name,
            description = description,
            status = ""
        )
        currentEpicDao.insert(epicEntity)
    }

    override suspend fun getCreatedEpic(): CurrentEpicEntity =
        currentEpicDao.getCreatedEpic(generateKey())

    private fun generateKey(): Long {
        val sdf = SimpleDateFormat("yyyyMMddhhmmss", Locale.CANADA)
        val currentDate = sdf.format(Date())
        return currentDate.toLong()
    }
}
