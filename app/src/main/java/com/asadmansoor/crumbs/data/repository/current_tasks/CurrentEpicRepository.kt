package com.asadmansoor.crumbs.data.repository.current_tasks

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity

interface CurrentEpicRepository {
    suspend fun getCurrentEpic(): LiveData<List<CurrentEpicEntity>>
    fun saveEpic(task: String)
}
