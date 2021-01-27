package com.asadmansoor.crumbs.ui.active_epic.create.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EpicViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epicName = MutableLiveData<String>("")
    val epicDescription = MutableLiveData<String>("")

    val nameValid = MediatorLiveData<Boolean>().apply {
        addSource(epicName) {
            val valid = isNameValid(it)
            value = valid
        }
    }

    val descriptionValid = MediatorLiveData<Boolean>().apply {
        addSource(epicDescription) {
            val valid = isDescriptionValid(it)
            value = valid
        }
    }

    val createdEpic = MediatorLiveData<CurrentEpic>()

    fun createNewEpic(name: String, description: String) {
        GlobalScope.launch {
            currentEpicRepository.createEpic(name = name, description = description)
            createdEpic.postValue(currentEpicRepository.getEpicByNameDescription(name, description))
        }
    }

    private fun isNameValid(input: String): Boolean {
        return input.isNotEmpty() && input.length <= 16
    }

    private fun isDescriptionValid(input: String): Boolean {
        return input.isNotEmpty() && input.length <= 36
    }
}
