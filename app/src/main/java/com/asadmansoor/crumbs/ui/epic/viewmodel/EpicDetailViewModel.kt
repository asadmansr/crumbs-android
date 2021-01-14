package com.asadmansoor.crumbs.ui.epic.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.domain.Story
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.data.repository.stories.StoriesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EpicDetailViewModel(
    private val currentEpicRepository: CurrentEpicRepository,
    private val storiesRepository: StoriesRepository
) : ViewModel() {

    val epic = MediatorLiveData<CurrentEpic>()
    val stories = MediatorLiveData<List<Story>>()

    fun getEpic(id: String) {
        GlobalScope.launch {
            epic.postValue(currentEpicRepository.getEpicById(id = id))
        }
    }

    fun deleteEpic(id: String) {
        GlobalScope.launch {
            currentEpicRepository.deleteEpic(id)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun updateStatus(id: String, status: Int) {
        GlobalScope.launch {
            currentEpicRepository.updateEpicStatus(id = id, status = status)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun completeEpic(id: String, currentEpic: CurrentEpic) {
        GlobalScope.launch {
            currentEpicRepository.completeEpic(id = id, epic = currentEpic)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun getStories() {
        
    }
}
