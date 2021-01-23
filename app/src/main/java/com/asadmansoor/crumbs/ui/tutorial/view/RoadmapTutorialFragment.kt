package com.asadmansoor.crumbs.ui.tutorial.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.RoadmapTutorialViewModel
import com.asadmansoor.crumbs.ui.tutorial.viewmodel.RoadmapTutorialViewModelFactory
import kotlinx.android.synthetic.main.fragment_roadmap_tutorial.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber


class RoadmapTutorialFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: RoadmapTutorialViewModelFactory by instance()
    private lateinit var viewModel: RoadmapTutorialViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_roadmap_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RoadmapTutorialViewModel::class.java)

        val viewPager: ViewPager2? = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.btn_roadmap_back.setOnClickListener {
            viewPager?.currentItem = 1
        }

        view.btn_roadmap_finish.setOnClickListener {
            showCustomViewDialog()
        }
    }

    private fun showCustomViewDialog() {
        MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            title(R.string.get_started)

            customView(R.layout.sheet_tutorial, scrollable = true, horizontalPadding = true)

            positiveButton(R.string.get_started) { dialog ->
                val input: EditText = dialog.getCustomView().findViewById(R.id.sheet_tutorial_name)
                val nameString = input.text.toString()
                if (nameString.isNotEmpty() || nameString.isNotBlank()) {
                    completeUserTutorial(nameString)
                }
            }

            negativeButton(android.R.string.cancel)
        }
    }

    private fun completeUserTutorial(name: String) {
        viewModel.doneUserTutorial(name = name)
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Timber.d("New user added: $user")
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
