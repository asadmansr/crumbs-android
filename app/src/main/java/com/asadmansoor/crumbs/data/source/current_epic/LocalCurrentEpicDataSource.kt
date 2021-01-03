package com.asadmansoor.crumbs.data.source.current_epic

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.internal.Result

interface LocalCurrentEpicDataSource {
    val createEpicResult: LiveData<Result<Any>>

    suspend fun getCurrentEpics(): LiveData<List<CurrentEpicEntity>>
    suspend fun createEpic(name: String, description: String)
}
