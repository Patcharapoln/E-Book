package com.example.map.ebook.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle

class AddCartDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Add this book to cart ?")
                .setPositiveButton("ADD", DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                })
                .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        return builder.create()
    }
}