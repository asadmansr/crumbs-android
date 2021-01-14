package com.asadmansoor.crumbs.ui.tutorial.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import com.asadmansoor.crumbs.data.repository.user.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TertiaryTutorialViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val user = MediatorLiveData<UserEntity>().apply {
        GlobalScope.launch {
            postValue(userRepository.loadUser())
        }
    }

    fun doneUserTutorial(name: String) {
        GlobalScope.launch {
            userRepository.saveUser(name = name)
            user.postValue(userRepository.loadUser())
        }
    }
}
