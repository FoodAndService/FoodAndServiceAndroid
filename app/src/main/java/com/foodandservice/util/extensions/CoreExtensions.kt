package com.foodandservice.util.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.foodandservice.R
import com.foodandservice.databinding.DialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

object CoreExtensions {
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.showDialog(
        title: String,
        description: String,
        btnPositiveLabel: String? = null,
        btnNegativeLabel: String? = null,
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
            btnPositiveLabel?.let { btnDialogPositive.text = it }
            btnNegativeLabel?.let { btnDialogNegative.text = it }

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

    fun Fragment.showAccountSettingsBottomSheet(
        layout: Int, onBtnActionClick: (() -> Unit)? = null
    ) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        layoutInflater.inflate(layout, null).also {
            dialog.setContentView(it)
            dialog.show()
        }.findViewById<Button>(R.id.btnBottomSheetAction).setOnClickListener {
            onBtnActionClick?.invoke()
        }
    }

    fun Fragment.showHomeFilterBottomSheet(
        layout: Int,
        onBtnRecommendedClick: (() -> Unit)? = null,
        onBtnPopularClick: (() -> Unit)? = null,
        onBtnValoratedClick: (() -> Unit)? = null,
        onBtnPriceLowClick: (() -> Unit)? = null,
        onBtnPriceMediumClick: (() -> Unit)? = null,
        onBtnPriceHighClick: (() -> Unit)? = null,
    ) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val inflatedLayout = layoutInflater.inflate(layout, null).also {
            dialog.setContentView(it)
            dialog.show()
        }

        inflatedLayout.apply {
            findViewById<TextView>(R.id.btnRecommended).setOnClickListener {
                onBtnRecommendedClick?.invoke()
            }
            findViewById<TextView>(R.id.btnPopular).setOnClickListener {
                onBtnPopularClick?.invoke()
            }
            findViewById<TextView>(R.id.btnValorated).setOnClickListener {
                onBtnValoratedClick?.invoke()
            }
            findViewById<Button>(R.id.btnPriceLow).setOnClickListener {
                onBtnPriceLowClick?.invoke()
            }
            findViewById<Button>(R.id.btnPriceMedium).setOnClickListener {
                onBtnPriceMediumClick?.invoke()
            }
            findViewById<Button>(R.id.btnPriceHigh).setOnClickListener {
                onBtnPriceHighClick?.invoke()
            }
        }
    }
}