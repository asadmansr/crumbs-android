package com.asadmansoor.crumbs.data.repository.current_tasks

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.dao.CurrentTasksDao
import com.asadmansoor.crumbs.data.db.entity.CurrentTaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentTasksRepositoryImpl(
    private val currentTasksDao: CurrentTasksDao
) : CurrentTasksRepository {

    override suspend fun getCurrentTasks(): LiveData<List<CurrentTaskEntity>> {
        return withContext(Dispatchers.IO) {
            return@withContext currentTasksDao.getCurrentTasks()
        }
    }

    override fun saveTask(task: String) {
        val currentTaskEntity = CurrentTaskEntity(task = task, completed = false, createdAt = 0)
        GlobalScope.launch(Dispatchers.IO){
            currentTasksDao.upsert(currentTask = currentTaskEntity)
        }
    }
}
