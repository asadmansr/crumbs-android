package com.asadmansoor.crumbs.ui.completed.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository

class CompletedEpicDetailViewModelFactory(
    private val completedEpicRepository: CompletedEpicRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CompletedEpicDetailViewModel(
            completedEpicRepository
        ) as T
    }
}