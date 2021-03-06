package com.asadmansoor.crumbs.ui.completed_epic.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.ui.completed_epic.list.item.CompletedEpicItem
import com.asadmansoor.crumbs.ui.completed_epic.list.viewmodel.CompletedEpicViewModel
import com.asadmansoor.crumbs.ui.completed_epic.list.viewmodel.CompletedEpicViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_completed_epic.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber


class CompletedEpicFragment : Fragment(), KodeinAware, View.OnClickListener {

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
        btn_navigate_back.setOnClickListener(this)
        getCompletedEpics()
    }

    private fun getCompletedEpics() {
        viewModel.getEpics()
        viewModel.epics.observe(viewLifecycleOwner, Observer { epics ->
            Timber.d("Loaded completed epics: $epics")
            if (epics.isEmpty()){
                tv_completed_empty.visibility = View.VISIBLE
            } else {
                tv_completed_empty.visibility = View.GONE
            }
            initRecyclerView(epics.toCompletedItem())
        })
    }

    private fun List<CompletedEpic>.toCompletedItem(): List<CompletedEpicItem> {
        return this.map {
            CompletedEpicItem(it)
        }
    }

    private fun initRecyclerView(items: List<CompletedEpicItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        rv_completed_epic.apply {
            layoutManager = LinearLayoutManager(this@CompletedEpicFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            val key = (item as CompletedEpicItem).epicItem.epicId
            val action =
                CompletedEpicFragmentDirections.actionCompletedEpicFragmentToCompletedEpicDetailFragment(
                    key
                )
            requireView().findNavController().navigate(action)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_navigate_back -> {
                navigateBack()
            }
        }
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }
}
