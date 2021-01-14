package com.asadmansoor.crumbs.ui.completed.detail.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CompletedEpicDetailViewModel(
    private val completedEpicRepository: CompletedEpicRepository
) : ViewModel() {

    val epic = MediatorLiveData<CompletedEpic>()

    fun getEpic(id: String) {
        GlobalScope.launch {
            epic.postValue(completedEpicRepository.getEpicById(id = id))
        }
    }

    fun deleteEpic(id: String) {
        GlobalScope.launch {
            completedEpicRepository.deleteCompletedEpic(id = id)
            epic.postValue(completedEpicRepository.getEpicById(id = id))
        }
    }
}
