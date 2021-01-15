package com.asadmansoor.crumbs.ui.tutorial.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
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
            //completeUserTutorial()
            showCustomViewDialog(BottomSheet())
        }
    }

    private fun showCustomViewDialog(dialogBehavior: DialogBehavior = ModalDialog) {
        val dialog = MaterialDialog(requireContext(), dialogBehavior).show {
            title(R.string.get_started)
            customView(R.layout.sheet_tutorial, scrollable = true, horizontalPadding = true)
            positiveButton(R.string.get_started) { dialog ->
                val input: EditText = dialog.getCustomView() .findViewById(R.id.sheet_tutorial_name)
                val nameString = input.text.toString()
                Log.d("myapp_sheet", nameString)
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
