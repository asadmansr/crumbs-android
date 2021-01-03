package com.asadmansoor.crumbs.ui.epic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.internal.Result

class EpicViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    suspend fun createEpic(name: String, description: String): LiveData<Result<Any>> {
        return currentEpicRepository.saveEpic(name = name, description = description)
    }
}
