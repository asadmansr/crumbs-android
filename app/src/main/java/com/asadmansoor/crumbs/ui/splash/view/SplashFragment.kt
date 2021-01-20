package com.asadmansoor.crumbs.ui.splash.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModel
import com.asadmansoor.crumbs.ui.splash.viewmodel.SplashViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class SplashFragment : Fragment(), KodeinAware {

    private val splashDelay: Long = 3000

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: SplashViewModelFactory by instance()
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
        processInitialStartup()
    }

    private fun processInitialStartup() {
        Handler().postDelayed({
            loadUserData()
        }, splashDelay)
    }

    private fun loadUserData() {
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Log.d("myapp", "$user")
            if ((user != null) && (user.tutorialCompleted)) {
                navigateToDashboard()
            } else {
                navigateToTutorial()
            }
        })
    }

    private fun navigateToTutorial() {
        requireView().findNavController()
            .navigate(R.id.action_splashFragment_to_tutorialViewPagerFragment)
    }

    private fun navigateToDashboard() {
        requireView().findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
    }
}
