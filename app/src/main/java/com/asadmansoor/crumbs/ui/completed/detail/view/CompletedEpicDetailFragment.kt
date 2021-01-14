package com.asadmansoor.crumbs.ui.completed.detail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.asadmansoor.crumbs.ui.completed.detail.viewmodel.CompletedEpicDetailViewModel
import com.asadmansoor.crumbs.ui.completed.detail.viewmodel.CompletedEpicDetailViewModelFactory
import com.asadmansoor.crumbs.ui.completed.list.viewmodel.CompletedEpicViewModelFactory
import kotlinx.android.synthetic.main.fragment_completed_epic_detail.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CompletedEpicDetailFragment : Fragment(), KodeinAware, View.OnClickListener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: CompletedEpicDetailViewModelFactory by instance()

    private val args: CompletedEpicDetailFragmentArgs by navArgs()
    private lateinit var viewModel: CompletedEpicDetailViewModel
    private lateinit var completedEpic: CompletedEpic


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_epic_detail, container, false)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CompletedEpicDetailViewModel::class.java)
        return view
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_navigate_back -> {
                navigateBack()
            }
            R.id.btn_delete -> {
                deleteEpic(args.completedEpicId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_navigate_back.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        bindUI(args.completedEpicId)
    }

    private fun bindUI(id: String) {
        viewModel.getEpic(id)
        viewModel.epic.observe(viewLifecycleOwner, Observer { epic ->
            Log.d("myapp_c_epic_detail", "$epic")
            if (epic != null) {
                if (epic.epicId.isEmpty()) {
                    navigateBack()
                } else {
                    completedEpic = epic
                }
            } else {
                navigateBack()
            }
        })
    }

    private fun deleteEpic(id: String) {
        viewModel.deleteEpic(id)
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }
}
