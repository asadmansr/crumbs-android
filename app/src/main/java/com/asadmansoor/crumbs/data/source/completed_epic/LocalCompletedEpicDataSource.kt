package com.asadmansoor.crumbs.data.source.completed_epic

import com.asadmansoor.crumbs.data.domain.CompletedEpic

interface LocalCompletedEpicDataSource {

    suspend fun getCompletedEpics(): List<CompletedEpic>

    suspend fun completedEpic(epic: CompletedEpic)

    suspend fun deleteCompletedEpic(id: Int)
}
