package com.asadmansoor.crumbs.ui.tutorial

import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.repository.user.UserRepository

class TertiaryTutorialViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun doneUserTutorial() {
        userRepository.saveUser(doneTutorial = true)
    }
}
