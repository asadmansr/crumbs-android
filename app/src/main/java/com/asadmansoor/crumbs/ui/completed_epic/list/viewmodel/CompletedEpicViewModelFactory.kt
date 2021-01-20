package com.asadmansoor.crumbs.ui.completed_epic.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository

class CompletedEpicViewModelFactory(
    private val completedEpicRepository: CompletedEpicRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CompletedEpicViewModel(completedEpicRepository) as T
    }
}