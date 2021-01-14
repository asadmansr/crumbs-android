package com.asadmansoor.crumbs.ui.tutorial.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.TertiaryTutorialViewModel
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.TertiaryTutorialViewModelFactory
import kotlinx.android.synthetic.main.fragment_tertiary_tutorial.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class TertiaryTutorialFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: TertiaryTutorialViewModelFactory by instance()
    private lateinit var viewModel: TertiaryTutorialViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tertiary_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TertiaryTutorialViewModel::class.java)

        val viewPager: ViewPager2? = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.btn_tertiary_back.setOnClickListener {
            viewPager?.currentItem = 1
        }

        view.btn_tertiary_finish.setOnClickListener {
            completeUserTutorial()
        }
    }

    private fun completeUserTutorial() {
        viewModel.doneUserTutorial(name = "Bob")
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Log.d("myapp_tutorial", "$user")
            if ((user != null) && (user.tutorialCompleted)) {
                navigateToDashboard()
            }
        })
    }

    private fun navigateToDashboard() {
        requireView().findNavController()
            .navigate(R.id.action_tutorialViewPagerFragment_to_dashboardFragment)
    }
}
