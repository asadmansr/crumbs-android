package com.asadmansoor.crumbs

import android.app.Application
import com.asadmansoor.crumbs.data.db.CrumbsDatabase
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepository
import com.asadmansoor.crumbs.data.repository.completed_epic.CompletedEpicRepositoryImpl
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepositoryImpl
import com.asadmansoor.crumbs.data.repository.user.UserRepository
import com.asadmansoor.crumbs.data.repository.user.UserRepositoryImpl
import com.asadmansoor.crumbs.data.source.completed_epic.LocalCompletedEpicDataSource
import com.asadmansoor.crumbs.data.source.completed_epic.LocalCompletedEpicDataSourceImpl
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSource
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSourceImpl
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSource
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSourceImpl
import com.asadmansoor.crumbs.ui.completed.list.viewmodel.CompletedEpicViewModelFactory
import com.asadmansoor.crumbs.ui.dashboard.DashboardViewModelFactory
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicDetailViewModelFactory
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicViewModelFactory
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModelFactory
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.TertiaryTutorialViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CrumbsApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CrumbsApplication))

        bind() from singleton { CrumbsDatabase(instance()) }
        bind() from singleton { instance<CrumbsDatabase>().userDao() }
        bind() from singleton { instance<CrumbsDatabase>().currentEpicDao() }
        bind() from singleton { instance<CrumbsDatabase>().completedEpicDao() }

        bind<LocalUserDataSource>() with singleton { LocalUserDataSourceImpl(instance()) }
        bind<LocalCurrentEpicDataSource>() with singleton { LocalCurrentEpicDataSourceImpl(instance()) }
        bind<LocalCompletedEpicDataSource>() with singleton {
            LocalCompletedEpicDataSourceImpl(
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

        bind() from provider {
            SplashViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            TertiaryTutorialViewModelFactory(
                instance()
            )
        }

        bind() from provider { DashboardViewModelFactory(instance()) }

        bind() from provider {
            EpicViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            EpicDetailViewModelFactory(
                instance()
            )
        }

        bind() from provider {
            CompletedEpicViewModelFactory(
                instance()
            )
        }
    }
}
