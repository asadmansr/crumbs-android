package com.asadmansoor.crumbs.ui.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.databinding.FragmentDashboardBinding
import com.asadmansoor.crumbs.ui.dashboard.item.CurrentEpicItem
import com.asadmansoor.crumbs.ui.dashboard.viewmodel.DashboardViewModel
import com.asadmansoor.crumbs.ui.dashboard.viewmodel.DashboardViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber


class DashboardFragment : Fragment(), KodeinAware, View.OnClickListener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: DashboardViewModelFactory by instance()

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        loadCurrentEpics()
    }

    private fun bindUI() {
        binding.fabDashboardCreate.setOnClickListener(this)
        binding.btnCompletedEpics.setOnClickListener(this)
        binding.btnFilterAll.setOnClickListener(this)
        binding.btnFilterNotStarted.setOnClickListener(this)
        binding.btnFilterPaused.setOnClickListener(this)
        binding.btnFilterProgress.setOnClickListener(this)
    }

    private fun loadCurrentEpics() {
        viewModel.getEpics()
        viewModel.epics.observe(viewLifecycleOwner, Observer { epics ->
            Timber.d("Current epics: $epics")
            initRecyclerView(epics.toCurrentEpicItem())
        })
    }

    private fun List<CurrentEpic>.toCurrentEpicItem(): List<CurrentEpicItem> {
        return this.map {
            CurrentEpicItem(it)
        }
    }

    private fun initRecyclerView(items: List<CurrentEpicItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        binding.rvDashboardEpics.apply {
            layoutManager = LinearLayoutManager(this@DashboardFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            val key = (item as CurrentEpicItem).epicItem.epicId
            navigateToEpicDetail(key)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab_dashboard_create -> {
                navigateToCreateEpic()
            }
            R.id.btn_completed_epics -> {
                navigateToCompletedEpics()
            }
            R.id.btn_filter_all -> {
                filterEpic(-1)
                binding.tvDashboardTitle.text = getString(R.string.filter_all)
            }
            R.id.btn_filter_not_started -> {
                filterEpic(0)
                binding.tvDashboardTitle.text = getString(R.string.filter_not_started)
            }
            R.id.btn_filter_paused -> {
                filterEpic(1)
                binding.tvDashboardTitle.text = getString(R.string.filter_paused)
            }
            R.id.btn_filter_progress -> {
                filterEpic(2)
                binding.tvDashboardTitle.text = getString(R.string.filter_in_progress)
            }
        }
    }

    private fun filterEpic(filter: Int) {
        viewModel.getEpicsByFilter(filter)
    }

    private fun navigateToCreateEpic() {
        requireView().findNavController().navigate(R.id.action_dashboardFragment_to_epicFragment)
    }

    private fun navigateToEpicDetail(key: String) {
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToEpicDetailFragment(
                key
            )
        requireView().findNavController().navigate(action)
    }

    private fun navigateToCompletedEpics() {
        requireView().findNavController()
            .navigate(R.id.action_dashboardFragment_to_completedEpicFragment)
    }
}
