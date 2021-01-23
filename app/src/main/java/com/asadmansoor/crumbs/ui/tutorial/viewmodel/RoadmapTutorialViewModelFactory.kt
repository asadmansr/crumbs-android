package com.asadmansoor.crumbs.ui.tutorial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.data.repository.user.UserRepository

class RoadmapTutorialViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoadmapTutorialViewModel(
            userRepository
        ) as T
    }
}
