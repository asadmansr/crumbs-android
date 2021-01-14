package com.asadmansoor.crumbs.data.repository.stats

import com.asadmansoor.crumbs.data.db.entity.AnalyticsEntity

interface StatsRepository {

    suspend fun getStats(): AnalyticsEntity
}
