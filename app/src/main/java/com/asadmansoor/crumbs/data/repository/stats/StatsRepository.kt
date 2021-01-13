package com.asadmansoor.crumbs.data.repository.stats

import com.asadmansoor.crumbs.data.db.entity.StatsEntity

interface StatsRepository {

    suspend fun getStats(): StatsEntity
}
