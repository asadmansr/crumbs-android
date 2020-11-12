package com.asadmansoor.crumbs

import android.app.Application
import com.asadmansoor.crumbs.data.db.CrumbsDatabase
import com.asadmansoor.crumbs.data.repository.UserRepository
import com.asadmansoor.crumbs.data.repository.UserRepositoryImpl
import com.asadmansoor.crumbs.ui.splash.SplashViewModelFactory
import com.asadmansoor.crumbs.ui.tutorial.TertiaryTutorialViewModelFactory
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
        bind<UserRepository>() with singleton { UserRepositoryImpl(instance()) }
        bind() from provider { SplashViewModelFactory(instance()) }
        bind() from provider { TertiaryTutorialViewModelFactory(instance()) }
    }
}
