package com.asadmansoor.crumbs.ui.dashboard

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epics = MediatorLiveData<List<CurrentEpic>>().apply {
        GlobalScope.launch {
            postValue(currentEpicRepository.getCurrentEpics())
        }
    }
}
