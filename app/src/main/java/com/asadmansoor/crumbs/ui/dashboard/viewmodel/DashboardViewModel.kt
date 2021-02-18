package com.asadmansoor.crumbs.ui.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epics = MutableLiveData<List<CurrentEpic>>()

    fun getEpics() {
        GlobalScope.launch {
            epics.postValue(currentEpicRepository.getCurrentEpics())
        }
    }

    fun getEpicsByFilter(filter: Int) {
        if (filter == -1) {
            GlobalScope.launch {
                epics.postValue(currentEpicRepository.getCurrentEpics())
            }
        } else {
            GlobalScope.launch {
                epics.postValue(currentEpicRepository.getCurrentEpicsByFilter(filter))
            }
        }
    }
}
