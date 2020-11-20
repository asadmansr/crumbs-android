package com.asadmansoor.crumbs.ui.dashboard

import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.repository.current_tasks.CurrentTasksRepository
import com.asadmansoor.crumbs.internal.lazyDeferred

class DashboardViewModel(
    private val currentTasksRepository: CurrentTasksRepository
) : ViewModel() {

    val tasks by lazyDeferred {
        currentTasksRepository.getCurrentTasks()
    }
}
