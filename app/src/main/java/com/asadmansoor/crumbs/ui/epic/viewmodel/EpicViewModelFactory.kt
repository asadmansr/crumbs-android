package com.asadmansoor.crumbs.ui.epic.viewmodel

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