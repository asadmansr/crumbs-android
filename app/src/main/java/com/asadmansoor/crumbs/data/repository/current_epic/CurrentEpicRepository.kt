package com.asadmansoor.crumbs.data.repository.current_epic

import androidx.lifecycle.LiveData
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity

interface CurrentEpicRepository {
    suspend fun getCurrentEpic(): LiveData<List<CurrentEpicEntity>>
    fun saveEpic(name: String, description: String)
}
