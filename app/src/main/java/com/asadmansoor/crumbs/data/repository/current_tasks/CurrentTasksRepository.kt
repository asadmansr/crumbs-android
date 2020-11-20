package com.asadmansoor.crumbs.data.repository.current_tasks

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.CurrentTaskEntity

interface CurrentTasksRepository {
    suspend fun getCurrentTasks(): LiveData<List<CurrentTaskEntity>>
    fun saveTask(task: String)
}
