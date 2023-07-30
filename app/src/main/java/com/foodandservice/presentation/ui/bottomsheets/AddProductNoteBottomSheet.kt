package com.foodandservice.presentation.ui.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.doOnTextChanged
import com.foodandservice.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton

class AddProductNoteBottomSheet(
    private val productNote: String,
    private val onTextChanged: (String) -> Unit,
    private val onContinueClick: () -> Unit
) : BottomSheetDialogFragment() {
    private lateinit var view: View

    fun setLoadingState() {
        view.apply {
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            findViewById<MaterialButton>(R.id.btnContinue).apply {
                isEnabled = false
                text = ""
            }
        }
    }

    fun setIdleState() {
        view.apply {
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            findViewById<MaterialButton>(R.id.btnContinue).apply {
                isEnabled = true
                text = getString(R.string.btn_continue)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.bottom_sheet_add_product_note, container, false)

        view.apply {
            findViewById<MaterialButton>(R.id.btnContinue).setOnClickListener {
                onContinueClick()
            }
            findViewById<EditText>(R.id.tieProductNote).apply {
                doOnTextChanged { newText, _, _, _ ->
                    onTextChanged(newText.toString())
                }
                setText(productNote)
            }
        }

        return view
    }
}