package com.asadmansoor.crumbs.data.source.completed_epic

import com.asadmansoor.crumbs.data.db.dao.CompletedEpicDao
import com.asadmansoor.crumbs.data.domain.CompletedEpic
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

    override suspend fun completedEpic(epic: CompletedEpic) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCompletedEpic(id: Int) {
        TODO("Not yet implemented")
    }

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
}
