package com.foodandservice.util.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.foodandservice.R

object ContextExtensions {
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showDialog(
        title: String,
        description: String,
        btnPositiveLabel: String? = null,
        btnNegativeLabel: String? = null,
        onBtnPositiveClick: (() -> Unit)? = null,
        onBtnNegativeClick: (() -> Unit)? = null
    ) {
        val dialog = Dialog(this, R.style.Theme_Dialog)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        val dialogTitle = dialog.findViewById(R.id.tvDialogTitle) as TextView
        val dialogDesc = dialog.findViewById(R.id.tvDialogDesc) as TextView
        val dialogPosBtn = dialog.findViewById(R.id.btnDialogPositive) as TextView
        val dialogNegBtn = dialog.findViewById(R.id.btnDialogNegative) as TextView
        dialogTitle.text = title
        dialogDesc.text = description
        btnPositiveLabel?.let { dialogPosBtn.text = it }
        btnNegativeLabel?.let { dialogNegBtn.text = it }
        dialogPosBtn.setOnClickListener {
            onBtnPositiveClick?.invoke()
            dialog.dismiss()
        }
        dialogNegBtn.setOnClickListener {
            onBtnNegativeClick?.invoke()
            dialog.dismiss()
        }
        dialog.show()
    }
}