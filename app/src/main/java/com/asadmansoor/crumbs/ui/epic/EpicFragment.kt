package com.asadmansoor.crumbs.ui.epic

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.internal.Result
import com.asadmansoor.crumbs.ui.base.ScopedFragment
import kotlinx.coroutines.launch
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
                navigateBack()
            }
        }
    }

    private fun navigateBack() {
        requireView().findNavController().navigateUp()
    }

    private fun createEpic() = launch {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()

        val resultObs: LiveData<Result<Any>> = viewModel.createEpic(name, description)
        resultObs.observe(viewLifecycleOwner, Observer { result ->
            Log.d("myapp", "$result")

            when (result) {
                is Result.Success -> {
                    Log.d("myapp", "here")
                    navigateBack()
                }
                is Result.Error -> {
                    showAlertDialog("Invalid input", result.exception.message!!)
                }
                is Result.InProgress -> {

                }
            }
        })
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton(R.string.dialog_ok) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}
