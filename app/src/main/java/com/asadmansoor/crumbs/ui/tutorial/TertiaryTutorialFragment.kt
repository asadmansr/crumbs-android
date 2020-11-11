package com.asadmansoor.crumbs.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.asadmansoor.crumbs.R
import kotlinx.android.synthetic.main.fragment_tertiary_tutorial.view.*


class TertiaryTutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tertiary_tutorial, container, false)

        val viewPager: ViewPager2? = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.btn_tertiary_back.setOnClickListener {
            viewPager?.currentItem = 1
        }

        view.btn_tertiary_finish.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_tutorialViewPagerFragment_to_dashboardFragment)
        }

        return view
    }
}
