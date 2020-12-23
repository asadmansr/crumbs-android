package com.asadmansoor.crumbs.ui.epic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.ui.base.ScopedFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class EpicFragment : ScopedFragment(), KodeinAware, View.OnClickListener {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: EpicViewModelFactory by instance()
    private lateinit var viewModel: EpicViewModel

    private lateinit var backButton: ImageButton
    private lateinit var createEpicButton: Button

    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_epic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI(view)
    }

    private fun bindUI(view: View) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(EpicViewModel::class.java)

        backButton = view.findViewById(R.id.btn_navigate_back)
        backButton.setOnClickListener(this)

        createEpicButton = view.findViewById(R.id.btn_create_epic)
        createEpicButton.setOnClickListener(this)

        nameEditText = view.findViewById(R.id.et_epic_name)
        descriptionEditText = view.findViewById(R.id.et_epic_description)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_create_epic -> {
                createEpic()
            }
            R.id.btn_navigate_back -> {
                view.findNavController().navigateUp()
            }
        }
    }

    private fun createEpic() {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()
        viewModel.createEpic(name = name, description = description)
    }
}
