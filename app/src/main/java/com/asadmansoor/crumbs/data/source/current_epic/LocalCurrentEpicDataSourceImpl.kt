package com.asadmansoor.crumbs.data.source.current_epic

import com.asadmansoor.crumbs.data.db.dao.CurrentEpicDao
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import java.text.SimpleDateFormat
import java.util.*

class LocalCurrentEpicDataSourceImpl(
    private val currentEpicDao: CurrentEpicDao
) : LocalCurrentEpicDataSource {

    override suspend fun getCurrentEpics(): List<CurrentEpic> =
        currentEpicDao.getCurrentTasks().map {
            CurrentEpic(
                id = it.id,
                createdAt = it.createdAt,
                createdAtString = getDateTime(it.createdAt),
                lastUpdated = it.lastUpdated,
                lastUpdatedString = getDateTime(it.lastUpdated),
                key = it.key,
                title = it.title,
                description = it.description,
                status = it.status,
                statusString = getStatusString(it.status)
            )
        }

    override suspend fun createEpic(name: String, description: String) {

        val timestamp = generateTimestamp()

        val epicEntity = CurrentEpicEntity(
            createdAt = timestamp,
            lastUpdated = timestamp,
            key = generateKey(),
            title = name,
            description = description,
            status = 0
        )
        currentEpicDao.insert(epicEntity)
    }

    override suspend fun getCreatedEpic(): CurrentEpicEntity =
        currentEpicDao.getCreatedEpic(generateKey())

    override suspend fun getEpicById(id: Int): CurrentEpic  {
        val epic = currentEpicDao.getEpicById(id)
        return CurrentEpic(
            id = epic.id,
            createdAt = epic.createdAt,
            createdAtString = getDateTime(epic.createdAt),
            lastUpdated = epic.lastUpdated,
            lastUpdatedString = getDateTime(epic.lastUpdated),
            key = epic.key,
            title = epic.title,
            description = epic.description,
            status = epic.status,
            statusString = getStatusString(epic.status)
        )
    }

    private fun generateKey(): Long {
        val sdf = SimpleDateFormat("yyyyMMddhhmmss", Locale.CANADA)
        val currentDate = sdf.format(Date())
        return currentDate.toLong()
    }

    private fun generateTimestamp(): Long {
        return System.currentTimeMillis() / 1000
    }

    private fun getDateTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("MMM d, yyyy", Locale.CANADA)
        val date = (timestamp * 1000)
        return sdf.format(date)
    }

    private fun getStatusString(status: Int): String {
        return when(status) {
            0 -> "Not Started"
            1 -> "Paused"
            2 -> "In Progress"
            else -> "Unknown"
        }
    }
}
