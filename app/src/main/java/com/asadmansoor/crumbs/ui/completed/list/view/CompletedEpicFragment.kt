package com.asadmansoor.crumbs.ui.completed.list.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.ui.completed.list.viewmodel.CompletedEpicViewModel
import com.asadmansoor.crumbs.ui.completed.list.viewmodel.CompletedEpicViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CompletedEpicFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: CompletedEpicViewModelFactory by instance()
    private lateinit var viewModel: CompletedEpicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed_epic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CompletedEpicViewModel::class.java)
        getCompletedEpics()
    }

    private fun getCompletedEpics() {
        viewModel.getEpics()
        viewModel.epics.observe(viewLifecycleOwner, Observer { epics ->
            Log.d("myapp_completed", "$epics")

        })
    }
}
