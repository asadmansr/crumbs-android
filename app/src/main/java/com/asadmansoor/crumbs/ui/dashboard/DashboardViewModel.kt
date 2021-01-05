package com.asadmansoor.crumbs.ui.dashboard

import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.internal.lazyDeferred

class DashboardViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epics by lazyDeferred {
        //currentEpicRepository.getCurrentEpic()
    }
}
