package com.asadmansoor.crumbs.ui.splash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import com.asadmansoor.crumbs.data.repository.user.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val user = MutableLiveData<UserEntity>().apply {
        GlobalScope.launch {
            postValue(userRepository.loadUser())
        }
    }
}
