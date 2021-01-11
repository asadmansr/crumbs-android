package com.asadmansoor.crumbs.data.repository.completed_epic

import com.asadmansoor.crumbs.data.domain.CompletedEpic

interface CompletedEpicRepository {

    suspend fun getCompletedEpics(): List<CompletedEpic>

    suspend fun completedEpic(epic: CompletedEpic)

    suspend fun deleteCompletedEpic(id: Int)
}
