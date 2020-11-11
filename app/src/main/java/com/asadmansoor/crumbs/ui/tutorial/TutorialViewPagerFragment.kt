package com.asadmansoor.crumbs.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asadmansoor.crumbs.R
import kotlinx.android.synthetic.main.fragment_tutorial_view_pager.view.*


class TutorialViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tutorial_view_pager, container, false)

        val fragmentList: ArrayList<Fragment> = arrayListOf<Fragment>(
            PrimaryTutorialFragment(),
            SecondaryTutorialFragment(),
            TertiaryTutorialFragment()
        )

        val adapter: TutorialViewPagerAdapter = TutorialViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter
        return view
    }

}
