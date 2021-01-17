package com.asadmansoor.crumbs.ui.epic.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.db.entity.CurrentStoryEntity
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.asadmansoor.crumbs.data.domain.Story
import com.asadmansoor.crumbs.databinding.FragmentEpicDetailBinding
import com.asadmansoor.crumbs.ui.dashboard.CurrentTaskItem
import com.asadmansoor.crumbs.ui.epic.CurrentStoryItem
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicDetailViewModel
import com.asadmansoor.crumbs.ui.epic.viewmodel.EpicDetailViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
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

    private lateinit var currentEpic: CurrentEpic
    private var numOfStories: Int = 0


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
        val loadedId = args.epicId
        loadData(loadedId)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_navigate_back -> {
                navigateBack()
            }
            R.id.btn_delete -> {
                deleteEpic(args.epicId)
            }
            R.id.btn_status_paused -> {
                updateStatus(args.epicId, 1)
            }
            R.id.btn_status_progress -> {
                updateStatus(args.epicId, 2)
            }
            R.id.btn_status_done -> {
                updateStatus(args.epicId, 3)
            }
            R.id.btn_complete_epic -> {
                completeEpic()
            }
            R.id.btn_add_story -> {
                showCreateStoryDialog()
            }
        }
    }

    private fun bindUI() {
        binding.btnNavigateBack.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        binding.btnStatusProgress.setOnClickListener(this)
        binding.btnStatusPaused.setOnClickListener(this)
        binding.btnStatusDone.setOnClickListener(this)
        binding.btnCompleteEpic.setOnClickListener(this)
        binding.btnAddStory.setOnClickListener(this)
    }

    private fun loadData(id: String) {
        viewModel.getEpic(id)
        viewModel.epic.observe(viewLifecycleOwner, Observer { epic ->
            Log.d("myapp_epic_detail", "$epic")
            if (epic != null) {
                if (epic.epicId.isEmpty()) {
                    navigateBack()
                } else {
                    currentEpic = epic
                    binding.tvEpicTitle.text = epic.title
                    binding.tvEpicDescription.text = epic.description
                    binding.tvCreatedValue.text = epic.createdAtString
                    binding.tvUpdatedValue.text = epic.lastUpdatedString
                    binding.tvStatusValue.text = epic.statusString
                }
            } else {
                navigateBack()
            }
        })

        viewModel.getStories(id)
        viewModel.stories.observe(viewLifecycleOwner, Observer { stories ->
            Log.d("myapp_epic_d_stories", "$stories")
            if (stories != null){
                if (stories.isEmpty() && numOfStories != 0){
                    viewModel.getStories(id)
                } else {
                    if (numOfStories == 0){
                        numOfStories = stories.size
                    }
                    initRecyclerView(stories.toCurrentItem())
                }
            }
        })
    }

    private fun deleteEpic(id: String) {
        viewModel.deleteEpic(id)
    }

    private fun updateStatus(id: String, status: Int) {
        viewModel.updateStatus(id, status)
    }

    private fun completeEpic() {
        viewModel.completeEpic(args.epicId, currentEpic)
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }

    private fun showCreateStoryDialog() {
        MaterialDialog(requireContext()).show {
            title(text = "Your Title")
            message(text = "Your Message")
            cornerRadius(16f)
            input(maxLength = 32) { dialog, text ->
                // Text submitted with the action button
                Log.d("myapp_dialog", text.toString())
                viewModel.addStory(text.toString(), args.epicId)
                numOfStories++
            }
            positiveButton(R.string.btn_create)
            negativeButton(R.string.btn_cancel)
        }
    }

    private fun List<Story>.toCurrentItem(): List<CurrentStoryItem> {
        return this.map {
            CurrentStoryItem(it)
        }
    }

    private fun initRecyclerView(items: List<CurrentStoryItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        binding.rvStories.apply {
            layoutManager = LinearLayoutManager(this@EpicDetailFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(this@EpicDetailFragment.context, "clicked", Toast.LENGTH_SHORT).show()

            val mitem = (item as CurrentStoryItem).storyItem
            val currentStoryEntity = CurrentStoryEntity(
                title = mitem.title,
                completed = mitem.completed,
                epicId = mitem.epicId,
                storyId = mitem.storyId,
                createdAt = mitem.createdAt,
                lastUpdated = mitem.lastUpdated
            )

            val storyId = (item as CurrentStoryItem).storyItem.storyId
            val status = (item as CurrentStoryItem).storyItem.completed

            MaterialDialog(requireContext()).show {
                title(text = "hi")
                message(text = "message")
                cornerRadius(16f)
                positiveButton(text = "Update") {
                    viewModel.updateStory(storyId, !status)
                }
                neutralButton(text = "Cancel")
                negativeButton(text = "Delete") {
                    viewModel.deleteStory(currentStoryEntity, storyId)
                    numOfStories--
                }
            }
        }
    }
}
