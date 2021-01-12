package com.asadmansoor.crumbs.data.repository.completed_epic

import com.asadmansoor.crumbs.data.domain.CompletedEpic

interface CompletedEpicRepository {

    suspend fun getCompletedEpics(): List<CompletedEpic>

    suspend fun getEpicById(id: Int): CompletedEpic

    suspend fun deleteCompletedEpic(id: Int)
}
