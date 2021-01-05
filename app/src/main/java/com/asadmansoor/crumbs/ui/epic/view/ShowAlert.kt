package com.asadmansoor.crumbs.ui.epic.view

import android.app.AlertDialog
import android.content.Context
import com.asadmansoor.crumbs.R

object ShowAlert {
    private fun showAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton(R.string.dialog_ok) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}
