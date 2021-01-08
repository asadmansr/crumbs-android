package com.asadmansoor.crumbs.ui.epic.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.databinding.FragmentEpicDetailBinding
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicDetailViewModel
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_epic_detail.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class EpicDetailFragment : Fragment(), KodeinAware, View.OnClickListener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: EpicDetailViewModelFactory by instance()

    private val args: EpicDetailFragmentArgs by navArgs()
    private lateinit var viewModel: EpicDetailViewModel
    private lateinit var binding: FragmentEpicDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_epic_detail, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(EpicDetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        val loadedId = args.epicKey
        loadData(loadedId)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_navigate_back -> {
                navigateBack()
            }
            R.id.btn_delete -> {
                navigateBack()
            }
        }
    }

    private fun bindUI() {
        binding.btnNavigateBack.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
    }

    private fun loadData(id: Int) {
        viewModel.getEpic(id)
        viewModel.epic.observe(viewLifecycleOwner, Observer { epic ->
            Log.d("myapp_epic_detail", "$epic")
            if (epic != null) {
                binding.tvEpicTitle.text = epic.title
                binding.tvEpicDescription.text = epic.description
                binding.tvCreatedValue.text = epic.createdAtString
                binding.tvUpdatedValue.text = epic.lastUpdatedString
                binding.tvStatusValue.text = epic.statusString
            }
        })
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }
}
