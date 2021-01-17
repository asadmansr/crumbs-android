package com.asadmansoor.crumbs.data.source.analytics

import com.asadmansoor.crumbs.data.domain.Analytics

interface LocalAnalyticsDataSource {

    suspend fun getAnalytics(): Analytics

    suspend fun incrementCurrentEpic()

    suspend fun decrementCurrentEpic()

    suspend fun incrementCompletedEpic()

    suspend fun decrementCompletedEpic()

    suspend fun incrementCurrentStory()

    suspend fun decrementCurrentStory()

    suspend fun incrementCompletedStory()

    suspend fun decrementCompletedStory()
}