package com.asadmansoor.crumbs.ui.active_epic.detail.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
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
            storiesRepository.deleteAllStoriesOfEpic(id)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun updateStatus(id: String, status: Int) {
        GlobalScope.launch {
            currentEpicRepository.updateEpicStatus(id = id, status = status)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun completeEpic(id: String, currentEpic: CurrentEpic, currentStories: List<Story>) {
        GlobalScope.launch {
            currentEpicRepository.completeEpic(id = id, epic = currentEpic)
            storiesRepository.completeStories(currentStories)
            epic.postValue(currentEpicRepository.getEpicById(id))
        }
    }

    fun getStories(epicId: String) {
        GlobalScope.launch {
            stories.postValue(storiesRepository.getStoriesByEpicId(epicId))
        }
    }

    fun addStory(title: String, epicId: String) {
        GlobalScope.launch {
            storiesRepository.addStory(title, epicId)
            stories.postValue(storiesRepository.getStoriesByEpicId(epicId))
        }
    }

    fun deleteStory(currentStoryEntity: CurrentStoryEntity, epicId: String) {
        GlobalScope.launch {
            storiesRepository.deleteStory(currentStoryEntity)
            stories.postValue(storiesRepository.getStoriesByEpicId(epicId))
        }
    }

    fun updateStory(epicId: String, completed: Boolean) {
        GlobalScope.launch {
            storiesRepository.updateStoryStatus(epicId, completed)
            stories.postValue(storiesRepository.getStoriesByEpicId(epicId))
        }
    }
}
