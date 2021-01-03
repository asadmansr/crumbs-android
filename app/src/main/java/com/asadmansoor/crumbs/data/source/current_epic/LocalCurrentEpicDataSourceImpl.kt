package com.asadmansoor.crumbs.data.source.current_epic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asadmansoor.crumbs.data.db.dao.CurrentEpicDao
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.internal.InvalidInputException
import com.asadmansoor.crumbs.internal.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalCurrentEpicDataSourceImpl(
    private val currentEpicDao: CurrentEpicDao
) : LocalCurrentEpicDataSource {

    private val _createEpicResult = MutableLiveData<Result<Any>>()
    override val createEpicResult: LiveData<Result<Any>>
        get() = _createEpicResult

    override suspend fun getCurrentEpics(): LiveData<List<CurrentEpicEntity>> {
        return withContext(Dispatchers.IO) {
            return@withContext currentEpicDao.getCurrentTasks()
        }
    }

    override suspend fun createEpic(name: String, description: String) {
        try {
            val epic = createEpicEntity(name = name, description = description)
            insertEpic(epic = epic)
            _createEpicResult.postValue(Result.Success(epic))
        } catch (e: InvalidInputException) {
            Log.d("myapp", "invalid input")
            _createEpicResult.postValue(Result.Error(e))
        } catch (e: Exception) {
            Log.d("myapp", "anything else")
            _createEpicResult.postValue(Result.Error(e))
        }
    }

    private fun createEpicEntity(name: String, description: String): CurrentEpicEntity {
        if (name.isEmpty() || name.isBlank()) {
            throw InvalidInputException("Epic name cannot be empty.")
        }

        if (description.isEmpty() || description.isBlank()) {
            throw InvalidInputException("Epic description cannot be empty.")
        }

        return CurrentEpicEntity(
            createdAt = 0L,
            lastUpdated = 0L,
            title = name,
            description = description,
            status = ""
        )
    }

    private fun insertEpic(epic: CurrentEpicEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            currentEpicDao.insert(epic)
        }
    }
}
