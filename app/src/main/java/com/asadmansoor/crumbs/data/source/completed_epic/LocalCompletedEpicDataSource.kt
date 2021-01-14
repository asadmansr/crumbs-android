package com.asadmansoor.crumbs.data.source.completed_epic

import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.domain.CurrentEpic

interface LocalCompletedEpicDataSource {

    suspend fun getCompletedEpics(): List<CompletedEpic>

    suspend fun getEpicById(id: String): CompletedEpic

    suspend fun completeEpic(epic: CurrentEpic)

    suspend fun deleteCompletedEpic(id: String)
}
