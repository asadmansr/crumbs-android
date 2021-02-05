package com.asadmansoor.crumbs

import android.app.Application
import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.core.InputTransformer
import com.asadmansoor.crumbs.data.db.CrumbsDatabase
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepositoryImpl
import com.asadmansoor.crumbs.data.repository.completed_story.CompletedStoriesRepository
import com.asadmansoor.crumbs.data.repository.completed_story.CompletedStoriesRepositoryImpl
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepositoryImpl
import com.asadmansoor.crumbs.data.repository.stories.StoriesRepository
import com.asadmansoor.crumbs.data.repository.stories.StoriesRepositoryImpl
import com.asadmansoor.crumbs.data.repository.user.UserRepository
import com.asadmansoor.crumbs.data.repository.user.UserRepositoryImpl
import com.asadmansoor.crumbs.data.source.completed_epic.LocalCompletedEpicDataSource
import com.asadmansoor.crumbs.data.source.completed_epic.LocalCompletedEpicDataSourceImpl
import com.asadmansoor.crumbs.data.source.completed_stories.LocalCompletedStoryDataSource
import com.asadmansoor.crumbs.data.source.completed_stories.LocalCompletedStoryDataSourceImpl
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSource
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSourceImpl
import com.asadmansoor.crumbs.data.source.stories.LocalCurrentStoryDataSource
import com.asadmansoor.crumbs.data.source.stories.LocalCurrentStoryDataSourceImpl
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSource
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSourceImpl
import com.asadmansoor.crumbs.ui.active_epic.create.viewmodel.EpicViewModelFactory
import com.asadmansoor.crumbs.ui.active_epic.detail.viewmodel.EpicDetailViewModelFactory
import com.asadmansoor.crumbs.ui.completed_epic.detail.viewmodel.CompletedEpicDetailViewModelFactory
import com.asadmansoor.crumbs.ui.completed_epic.list.viewmodel.CompletedEpicViewModelFactory
import com.asadmansoor.crumbs.ui.dashboard.viewmodel.DashboardViewModelFactory
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModelFactory
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.RoadmapTutorialViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber
import timber.log.Timber.DebugTree


class CrumbsApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CrumbsApplication))

        bind() from singleton { CrumbsDatabase(instance()) }
        bind() from singleton { instance<CrumbsDatabase>().completedEpicDao() }
        bind() from singleton { instance<CrumbsDatabase>().completedStoryDao() }
        bind() from singleton { instance<CrumbsDatabase>().currentEpicDao() }
        bind() from singleton { instance<CrumbsDatabase>().currentStoryDao() }
        bind() from singleton { instance<CrumbsDatabase>().userDao() }

        bind() from singleton { GenerateTimeParameter() }
        bind() from singleton { InputTransformer() }

        bind<LocalUserDataSource>() with singleton {
            LocalUserDataSourceImpl(
                instance(),
                instance()
            )
        }
        bind<LocalCurrentEpicDataSource>() with singleton {
            LocalCurrentEpicDataSourceImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind<LocalCompletedEpicDataSource>() with singleton {
            LocalCompletedEpicDataSourceImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind<LocalCurrentStoryDataSource>() with singleton {
            LocalCurrentStoryDataSourceImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind<LocalCompletedStoryDataSource>() with singleton {
            LocalCompletedStoryDataSourceImpl(
                instance(),
                instance(),
                instance()
            )
        }

        bind<UserRepository>() with singleton {
            UserRepositoryImpl(
                instance()
            )
        }

        bind<CurrentEpicRepository>() with singleton {
            CurrentEpicRepositoryImpl(
                instance(),
                instance()
            )
        }

        bind<CompletedEpicRepository>() with singleton {
            CompletedEpicRepositoryImpl(
                instance()
            )
        }

        bind<StoriesRepository>() with singleton {
            StoriesRepositoryImpl(
                instance(),
                instance()
            )
        }
        bind<CompletedStoriesRepository>() with singleton {
            CompletedStoriesRepositoryImpl(
                instance()
            )
        }

        bind() from provider {
            SplashViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            RoadmapTutorialViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            DashboardViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            EpicViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            EpicDetailViewModelFactory(
                instance(),
                instance()
            )
        }

        bind() from provider {
            CompletedEpicViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            CompletedEpicDetailViewModelFactory(
                instance(),
                instance()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
