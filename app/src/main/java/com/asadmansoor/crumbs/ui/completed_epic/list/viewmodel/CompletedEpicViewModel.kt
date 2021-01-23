package com.asadmansoor.crumbs.ui.completed_epic.list.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CompletedEpicViewModel(
    private val completedEpicRepository: CompletedEpicRepository
) : ViewModel() {

    val epics = MediatorLiveData<List<CompletedEpic>>()

    fun getEpics() {
        GlobalScope.launch {
            epics.postValue(completedEpicRepository.getCompletedEpics())
        }
    }
}
