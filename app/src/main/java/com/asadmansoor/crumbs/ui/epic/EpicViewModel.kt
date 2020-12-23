package com.asadmansoor.crumbs.ui.epic

import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.internal.lazyDeferred

class EpicViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epics by lazyDeferred {
        currentEpicRepository.getCurrentEpic()
    }

    fun createEpic(name: String, description: String) {
        currentEpicRepository.saveEpic(name = name, description = description)
    }
}
