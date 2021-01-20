package com.asadmansoor.crumbs.ui.tutorial.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.asadmansoor.crumbs.R
import kotlinx.android.synthetic.main.fragment_stories_tutorial.view.*


class StoriesTutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_stories_tutorial, container, false)

        val viewPager: ViewPager2? = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.btn_stories_back.setOnClickListener {
            viewPager?.currentItem = 0
        }

        view.btn_stories_next.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return view
    }
}
