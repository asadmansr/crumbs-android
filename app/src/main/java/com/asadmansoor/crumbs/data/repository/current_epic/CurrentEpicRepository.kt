package com.asadmansoor.crumbs.data.repository.current_epic

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.internal.Result

interface CurrentEpicRepository {
    suspend fun getCurrentEpic(): LiveData<List<CurrentEpicEntity>>
    suspend fun saveEpic(name: String, description: String): LiveData<Result<Any>>
}
