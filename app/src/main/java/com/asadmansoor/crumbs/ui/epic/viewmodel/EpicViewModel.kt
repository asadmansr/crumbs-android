package com.asadmansoor.crumbs.ui.epic.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.db.entity.UserEntity
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
            val valid = isInputValid(it)
            Log.d("myapp", "name: " + valid.toString())
            value = valid
        }
    }

    val descriptionValid = MediatorLiveData<Boolean>().apply {
        addSource(epicDescription) {
            val valid = isInputValid(it)
            Log.d("myapp", "desc: " + valid.toString())
            value = valid
        }
    }

    val createdEpic = MediatorLiveData<CurrentEpicEntity>().apply {
        GlobalScope.launch {
            //postValue(currentEpicRepository.getCreatedEpic())
        }
    }

    fun createNewEpic(name: String, description: String) {
        GlobalScope.launch {
            currentEpicRepository.createEpic(name = name, description = description)
            //createdEpic.postValue(currentEpicRepository.getCreatedEpic())
        }
    }

    private fun isInputValid(input: String): Boolean {
        return input.isNotEmpty()
    }
}
