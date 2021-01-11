package com.asadmansoor.crumbs.ui.epic.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EpicDetailViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epic = MediatorLiveData<CurrentEpic>()

    fun getEpic(id: Int) {
        GlobalScope.launch {
            epic.postValue(currentEpicRepository.getEpicById(id = id))
        }
    }

    fun deleteEpic(id: Int) {
        GlobalScope.launch {
            currentEpicRepository.deleteEpic(id)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun updateStatus(id: Int, status: Int) {
        GlobalScope.launch {
            currentEpicRepository.updateEpicStatus(id = id, status = status)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun completeEpic(id: Int, currentEpic: CurrentEpic) {
        GlobalScope.launch {
            currentEpicRepository.completeEpic(id = id, epic = currentEpic)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }
}
