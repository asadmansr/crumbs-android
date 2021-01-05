package com.asadmansoor.crumbs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.db.CrumbsDatabase
import com.asadmansoor.crumbs.data.db.entity.UserEntity
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepository
import com.asadmansoor.crumbs.data.repository.current_epic.CurrentEpicRepositoryImpl
import com.asadmansoor.crumbs.data.repository.user.UserRepository
import com.asadmansoor.crumbs.data.repository.user.UserRepositoryImpl
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSource
import com.asadmansoor.crumbs.data.source.current_epic.LocalCurrentEpicDataSourceImpl
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSource
import com.asadmansoor.crumbs.data.source.user.LocalUserDataSourceImpl
import com.asadmansoor.crumbs.ui.dashboard.DashboardViewModelFactory
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicViewModelFactory
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModel
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModelFactory
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.TertiaryTutorialViewModelFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class SplashViewModelTest: KodeinAware {

    @Mock
    private lateinit var viewModel: SplashViewModel

    @Mock
    private lateinit var userData: MediatorLiveData<UserEntity>

    @Mock
    private lateinit var observer: Observer<UserEntity>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    override val kodein = Kodein {
        bind() from singleton { CrumbsDatabase(instance()) }
        bind() from singleton { instance<CrumbsDatabase>().userDao() }
        bind() from singleton { instance<CrumbsDatabase>().currentEpicDao() }
        bind<LocalCurrentEpicDataSource>() with singleton { LocalCurrentEpicDataSourceImpl(instance()) }
        bind<LocalUserDataSource>() with singleton { LocalUserDataSourceImpl(instance()) }
        bind<UserRepository>() with singleton {
            UserRepositoryImpl(
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
        bind<CurrentEpicRepository>() with singleton {
            CurrentEpicRepositoryImpl(
                instance()
            )
        }
        bind() from provider { DashboardViewModelFactory(instance()) }
        bind() from provider {
            EpicViewModelFactory(
                instance()
            )
        }
    }

    private val repository: UserRepository by instance()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(SplashViewModel(repository))
        userData = viewModel.user
    }

    @Test
    fun updateUserTutorialTest() {
        val user = UserEntity(doneTutorial = false)
        Assert.assertNotNull(viewModel.user)
        viewModel.user.observeForever(observer)
        verify(observer).onChanged(user)
        Assert.assertNotNull(viewModel.user)
    }
}