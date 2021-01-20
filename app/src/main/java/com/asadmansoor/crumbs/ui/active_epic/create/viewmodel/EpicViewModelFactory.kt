package com.asadmansoor.crumbs.ui.active_epic.create.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository


class EpicViewModelFactory(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpicViewModel(
            currentEpicRepository
        ) as T
    }
}