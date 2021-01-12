package com.asadmansoor.crumbs.data.source.completed_epic

import com.asadmansoor.crumbs.data.db.dao.CompletedEpicDao
import com.asadmansoor.crumbs.data.db.entity.CompletedEpicEntity
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import java.text.SimpleDateFormat
import java.util.*

class LocalCompletedEpicDataSourceImpl(
    private val completedEpicDao: CompletedEpicDao
) : LocalCompletedEpicDataSource {

    override suspend fun getCompletedEpics(): List<CompletedEpic> =
        completedEpicDao.getCompletedEpic().map {
            CompletedEpic(
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

    override suspend fun getEpicById(id: Int): CompletedEpic {
        val epic = completedEpicDao.getEpicById(id)
        val completedEpic: CompletedEpic
        if (epic != null) {
            completedEpic = CompletedEpic(
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
        } else {
            completedEpic = CompletedEpic(
                id = -1,
                createdAt = -1,
                createdAtString = "",
                lastUpdated = -1,
                lastUpdatedString = "",
                key = -1,
                title = "",
                description = "",
                status = -1,
                statusString = ""
            )
        }

        return completedEpic
    }

    override suspend fun completeEpic(epic: CurrentEpic) {
        val epicEntity = CompletedEpicEntity(
            createdAt = epic.createdAt,
            lastUpdated = epic.lastUpdated,
            key = epic.key,
            title = epic.title,
            description = epic.description,
            status = 3,
            completedAt = generateTimestamp()
        )
        completedEpicDao.insert(epicEntity)
    }

    override suspend fun deleteCompletedEpic(id: Int) =
        completedEpicDao.deleteCompletedEpic(id = id)

    private fun getDateTime(timestamp: Long): String {
        if (timestamp == -1L) return ""
        val sdf = SimpleDateFormat("MMM d, yyyy", Locale.CANADA)
        val date = (timestamp * 1000)
        return sdf.format(date)
    }

    private fun getStatusString(status: Int): String {
        return when (status) {
            0 -> "Not Started"
            1 -> "Paused"
            2 -> "In Progress"
            3 -> "Done"
            else -> "Unknown"
        }
    }

    private fun generateTimestamp(): Long {
        return System.currentTimeMillis() / 1000
    }
}
