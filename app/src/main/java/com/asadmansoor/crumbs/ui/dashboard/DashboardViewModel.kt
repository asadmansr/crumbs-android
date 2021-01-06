package com.asadmansoor.crumbs.ui.dashboard

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.internal.lazyDeferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val currentEpicRepository: CurrentEpicRepository
) : ViewModel() {

    val epics = MediatorLiveData<List<CurrentEpicEntity>>().apply {
        GlobalScope.launch {
            postValue(currentEpicRepository.getCurrentEpics())
        }
    }
}
