package com.asadmansoor.crumbs.ui.active_epic.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.data.repository.stories.StoriesRepository

class EpicDetailViewModelFactory(
    private val currentEpicRepository: CurrentEpicRepository,
    private val storiesRepository: StoriesRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpicDetailViewModel(
            currentEpicRepository,
            storiesRepository
        ) as T
    }
}