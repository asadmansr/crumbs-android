package com.asadmansoor.crumbs.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.asadmansoor.crumbs.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


class SplashFragment : Fragment() {

    private val splashDelay: Long = 3000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processInitialStartup()
    }

    private fun processInitialStartup() {
        Timer().schedule(splashDelay) {
            GlobalScope.launch(Dispatchers.Main) {
                navigateToOnboard()
            }
        }
    }

    private fun navigateToOnboard() {
        requireView().findNavController().navigate(R.id.action_splashFragment_to_tutorialViewPagerFragment)
    }
}
