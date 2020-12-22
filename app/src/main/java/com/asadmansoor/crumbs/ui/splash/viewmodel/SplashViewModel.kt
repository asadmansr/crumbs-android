package com.asadmansoor.crumbs.ui.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.repository.user.UserRepository
import com.asadmansoor.crumbs.internal.lazyDeferred

class SplashViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val user by lazyDeferred {
        userRepository.getUser()
    }
}
