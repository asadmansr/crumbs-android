package com.asadmansoor.crumbs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asadmansoor.crumbs.data.core.GenerateTimeParameter
import com.asadmansoor.crumbs.data.core.InputTransformer
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
import com.asadmansoor.crumbs.ui.active_epic.create.viewmodel.EpicViewModelFactory
import com.asadmansoor.crumbs.ui.dashboard.viewmodel.DashboardViewModelFactory
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModel
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModelFactory
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.RoadmapTutorialViewModel
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
class SplashViewModelTest : KodeinAware {

    @Mock
    private lateinit var viewModel: SplashViewModel

    @Mock
    private lateinit var userData: MutableLiveData<UserEntity>

    @Mock
    private lateinit var observer: Observer<UserEntity>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    override val kodein = Kodein {
        bind() from singleton { CrumbsDatabase(instance()) }
        bind() from singleton { instance<CrumbsDatabase>().userDao() }
        bind() from singleton { instance<CrumbsDatabase>().currentEpicDao() }

        bind() from singleton { GenerateTimeParameter() }
        bind() from singleton { InputTransformer() }

        bind<LocalCurrentEpicDataSource>() with singleton {
            LocalCurrentEpicDataSourceImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind<LocalUserDataSource>() with singleton {
            LocalUserDataSourceImpl(
                instance(),
                instance()
            )
        }
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
            RoadmapTutorialViewModel(
                instance()
            )
        }
        bind<CurrentEpicRepository>() with singleton {
            CurrentEpicRepositoryImpl(
                instance(),
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
        val user = UserEntity(
            uid = "t",
            name = "t",
            accountCreated = 0L,
            tutorialCompleted = true
        )
        Assert.assertNotNull(viewModel.user)
        viewModel.user.observeForever(observer)
        verify(observer).onChanged(user)
        Assert.assertNotNull(viewModel.user)
    }
}