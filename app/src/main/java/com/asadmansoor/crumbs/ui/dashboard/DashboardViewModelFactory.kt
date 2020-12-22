package com.asadmansoor.crumbs.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.current_tasks.CurrentEpicRepository

class DashboardViewModelFactory(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(currentEpicRepository) as T
    }
}
