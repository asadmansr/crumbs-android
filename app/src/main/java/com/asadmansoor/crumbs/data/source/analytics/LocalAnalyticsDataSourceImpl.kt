package com.asadmansoor.crumbs.data.source.analytics

import com.asadmansoor.crumbs.data.db.dao.AnalyticsDao
import com.asadmansoor.crumbs.data.domain.Analytics

class LocalAnalyticsDataSourceImpl(
    private val analyticsDao: AnalyticsDao
) : LocalAnalyticsDataSource {

    override suspend fun getAnalytics(): Analytics {
        val analytics = analyticsDao.loadAnalytics()
        return Analytics(
            id = analytics.id,
            uid = analytics.uid,
            currentEpics = analytics.currentEpics,
            completedEpics = analytics.completedEpics,
            currentStories = analytics.currentStories,
            completedStories = analytics.completedStories
        )
    }

    override suspend fun incrementCurrentEpic() = analyticsDao.incrementCurrentEpic()

    override suspend fun decrementCurrentEpic() = analyticsDao.decrementCurrentEpic()

    override suspend fun incrementCompletedEpic() = analyticsDao.incrementCompletedEpic()

    override suspend fun decrementCompletedEpic() = analyticsDao.decrementCompletedEpic()

    override suspend fun incrementCurrentStory() = analyticsDao.incrementCurrentStory()

    override suspend fun decrementCurrentStory() = analyticsDao.decrementCurrentStory()

    override suspend fun incrementCompletedStory() = analyticsDao.incrementCompletedStory()

    override suspend fun decrementCompletedStory() = analyticsDao.incrementCompletedStory()
}
