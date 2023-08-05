package com.foodandservice.util

import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.presentation.ui.adapter.ProductExtraAdapter
import com.foodandservice.presentation.ui.adapter.RestaurantProductDietaryRestrictionAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

object FysBottomSheets {
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
        layout: Int = R.layout.bottom_sheet_product_allergens_intolerances,
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

    fun Fragment.showProductExtrasBottomSheet(
        layout: Int = R.layout.bottom_sheet_product_extras, productExtraAdapter: ProductExtraAdapter
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
        layout: Int = R.layout.bottom_sheet_home_filter,
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