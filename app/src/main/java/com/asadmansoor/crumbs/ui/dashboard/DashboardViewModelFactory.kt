package com.asadmansoor.crumbs.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.current_tasks.CurrentTasksRepository

class DashboardViewModelFactory(
    private val currentTasksRepository: CurrentTasksRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(currentTasksRepository) as T
    }
}
