package com.asadmansoor.crumbs.ui.active_epic.create.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.databinding.FragmentEpicBinding
import com.asadmansoor.crumbs.ui.active_epic.create.viewmodel.EpicViewModel
import com.asadmansoor.crumbs.ui.active_epic.create.viewmodel.EpicViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class EpicFragment : Fragment(), KodeinAware, View.OnClickListener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: EpicViewModelFactory by instance()

    private lateinit var viewModel: EpicViewModel
    private lateinit var binding: FragmentEpicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_epic, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(EpicViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() {
        binding.btnNavigateBack.setOnClickListener(this)
        binding.btnCreateEpic.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_create_epic -> {
                createEpic()
            }
            R.id.btn_navigate_back -> {
                navigateBack()
            }
        }
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }

    private fun createEpic() {
        val name = binding.etEpicName.text.toString()
        val description = binding.etEpicDescription.text.toString()

        viewModel.createNewEpic(name = name, description = description)
        viewModel.createdEpic.observe(viewLifecycleOwner, Observer { epic ->
            Timber.d("Created epic: $epic")
            if (epic != null || epic!!.title.isNotEmpty()) {
                navigateBack()
            }
        })
    }
}
