package com.foodandservice.util

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.presentation.ui.adapter.ProductExtraAdapter
import com.foodandservice.presentation.ui.adapter.RestaurantProductDietaryRestrictionAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

object FysBottomSheets {
    fun Fragment.showGenericBottomSheet(
        layout: Int
    ) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        layoutInflater.inflate(layout, null).also {
            dialog.setContentView(it)
            dialog.show()
        }
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

    fun Fragment.showAllergensAndIntolerancesBottomSheet(
        layout: Int,
        restaurantProductDietaryRestrictionAdapter: RestaurantProductDietaryRestrictionAdapter
    ) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val inflatedLayout = layoutInflater.inflate(layout, null).also {
            dialog.setContentView(it)
            dialog.show()
        }
        inflatedLayout.apply {
            findViewById<RecyclerView>(R.id.rvAllergenIntolerance).adapter =
                restaurantProductDietaryRestrictionAdapter
        }
    }

    fun Fragment.showAddProductNoteBottomSheet(
        layout: Int,
        productNote: String,
        onTextChanged: (String) -> Unit,
        onContinueClick: () -> Unit
    ) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val inflatedLayout = layoutInflater.inflate(layout, null).also {
            dialog.setContentView(it)
            dialog.behavior.peekHeight = 1000
            dialog.show()
        }
        inflatedLayout.apply {
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
    }

    fun Fragment.showProductExtrasBottomSheet(
        layout: Int, productExtraAdapter: ProductExtraAdapter
    ) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val inflatedLayout = layoutInflater.inflate(layout, null).also {
            dialog.setContentView(it)
            dialog.behavior.peekHeight = 1000
            dialog.show()
        }
        inflatedLayout.apply {
            findViewById<RecyclerView>(R.id.rvProductExtra).adapter = productExtraAdapter
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