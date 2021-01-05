package com.asadmansoor.crumbs.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.asadmansoor.crumbs.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class DashboardFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: DashboardViewModelFactory by instance()
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
        getTasks()

        floatingActionButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_dashboardFragment_to_epicFragment)
        }
    }

    private fun getTasks() = launch {
//        val userData: LiveData<List<CurrentEpicEntity>> = viewModel.epics.await()
//        userData.observe(viewLifecycleOwner, Observer { tasks ->
//            Log.d("myapp", "$tasks")
//            initRecyclerView(listOf("1","2","3").toCurrentItem())
//            if ((tasks != null) && (tasks.isNotEmpty())) {
//                tv_discover_title.visibility = View.GONE
//
//            } else {
//                tv_discover_title.visibility = View.VISIBLE
//            }
//        })
    }

    private fun List<String>.toCurrentItem() : List<CurrentTaskItem> {
        return this.map {
            CurrentTaskItem(it)
        }
    }

    private fun initRecyclerView(items: List<CurrentTaskItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@DashboardFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(this@DashboardFragment.context, "clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
