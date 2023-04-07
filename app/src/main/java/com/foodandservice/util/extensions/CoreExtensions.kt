package com.foodandservice.util.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.DialogLayoutBinding

object CoreExtensions {
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Activity.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.navigateBack() {
        findNavController().popBackStack()
    }

    fun Fragment.navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    fun Context.openAppSystemSettings() {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName, null)
        })
    }

    fun Activity.showDialog(
        title: String,
        description: String,
        btnPositiveLabel: String = getString(R.string.btn_confirm),
        btnNegativeLabel: String = getString(R.string.btn_cancel),
        onBtnPositiveClick: (() -> Unit)? = null,
        onBtnNegativeClick: (() -> Unit)? = null
    ) {
        val binding: DialogLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.dialog_layout, null, false
        )
        val dialog = Dialog(this, R.style.Theme_Dialog)

        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.apply {
            tvDialogTitle.text = title
            tvDialogDesc.text = description
            btnDialogNegative.text = btnNegativeLabel
            btnDialogPositive.text = btnPositiveLabel

            btnDialogPositive.setOnClickListener {
                onBtnPositiveClick?.invoke()
                dialog.dismiss()
            }

            btnDialogNegative.setOnClickListener {
                onBtnNegativeClick?.invoke()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    fun Fragment.showDialog(
        title: String,
        description: String,
        btnPositiveLabel: String = getString(R.string.btn_confirm),
        btnNegativeLabel: String = getString(R.string.btn_cancel),
        onBtnPositiveClick: (() -> Unit)? = null,
        onBtnNegativeClick: (() -> Unit)? = null
    ) {
        val binding: DialogLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()), R.layout.dialog_layout, null, false
        )
        val dialog = Dialog(requireContext(), R.style.Theme_Dialog)

        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.apply {
            tvDialogTitle.text = title
            tvDialogDesc.text = description
            btnDialogNegative.text = btnNegativeLabel
            btnDialogPositive.text = btnPositiveLabel

            btnDialogPositive.setOnClickListener {
                onBtnPositiveClick?.invoke()
                dialog.dismiss()
            }

            btnDialogNegative.setOnClickListener {
                onBtnNegativeClick?.invoke()
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}