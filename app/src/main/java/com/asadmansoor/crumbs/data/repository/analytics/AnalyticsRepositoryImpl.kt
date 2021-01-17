package com.asadmansoor.crumbs.data.repository.analytics

import com.asadmansoor.crumbs.data.domain.Analytics
import com.asadmansoor.crumbs.data.source.analytics.LocalAnalyticsDataSource

class AnalyticsRepositoryImpl(
    private val localAnalyticsDataSource: LocalAnalyticsDataSource
) : AnalyticsRepository {

    override suspend fun getAnalytics(): Analytics = localAnalyticsDataSource.getAnalytics()

    override suspend fun incrementCurrentEpic() = localAnalyticsDataSource.incrementCurrentEpic()

    override suspend fun decrementCurrentEpic() = localAnalyticsDataSource.decrementCurrentEpic()

    override suspend fun incrementCompletedEpic() =
        localAnalyticsDataSource.incrementCompletedEpic()

    override suspend fun decrementCompletedEpic() =
        localAnalyticsDataSource.decrementCompletedEpic()

    override suspend fun incrementCurrentStory() = localAnalyticsDataSource.incrementCurrentStory()

    override suspend fun decrementCurrentStory() = localAnalyticsDataSource.decrementCurrentStory()

    override suspend fun incrementCompletedStory() =
        localAnalyticsDataSource.incrementCompletedStory()

    override suspend fun decrementCompletedStory() =
        localAnalyticsDataSource.decrementCompletedStory()
}
