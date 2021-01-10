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
}
