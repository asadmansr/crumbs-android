package com.asadmansoor.crumbs.ui.completed_epic.detail.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.data.domain.CompletedStory
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository
import com.asadmansoor.crumbs.data.repository.completed_story.CompletedStoriesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CompletedEpicDetailViewModel(
    private val completedEpicRepository: CompletedEpicRepository,
    private val completedStoriesRepository: CompletedStoriesRepository
) : ViewModel() {

    val epic = MediatorLiveData<CompletedEpic>()
    val stories = MediatorLiveData<List<CompletedStory>>()

    fun getEpic(id: String) {
        GlobalScope.launch {
            epic.postValue(completedEpicRepository.getEpicById(id = id))
        }
    }

    fun deleteEpic(id: String) {
        GlobalScope.launch {
            completedEpicRepository.deleteCompletedEpic(id = id)
            completedStoriesRepository.deleteAllStoriesOfEpic(id)
            epic.postValue(completedEpicRepository.getEpicById(id = id))
        }
    }

    fun getStories(epicId: String) {
        GlobalScope.launch {
            stories.postValue(completedStoriesRepository.getCompletedStories(epicId))
        }
    }
}
