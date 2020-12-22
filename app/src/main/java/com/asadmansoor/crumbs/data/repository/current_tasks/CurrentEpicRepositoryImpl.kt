package com.asadmansoor.crumbs.data.repository.current_tasks

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.dao.CurrentEpicDao
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentEpicRepositoryImpl(
    private val currentEpicDao: CurrentEpicDao
) : CurrentEpicRepository {

    override suspend fun getCurrentEpic(): LiveData<List<CurrentEpicEntity>> {
        return withContext(Dispatchers.IO) {
            return@withContext currentEpicDao.getCurrentTasks()
        }
    }

    override fun saveEpic(task: String) {
        val currentTaskEntity = CurrentEpicEntity(createdAt = 0L, lastUpdated = 0L, title = "", description = "", status = "")
        GlobalScope.launch(Dispatchers.IO){
            currentEpicDao.insert(currentEpic = currentTaskEntity)
        }
    }
}
