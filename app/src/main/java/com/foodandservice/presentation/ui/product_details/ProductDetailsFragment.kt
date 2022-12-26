package com.foodandservice.presentation.ui.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentProductDetailsBinding
import com.foodandservice.domain.model.AllergenIntolerance
import com.foodandservice.presentation.ui.adapter.AllergenIntoleranceAdapter
import com.foodandservice.util.FysBottomSheets.showAllergiesAndIntolerancesBottomSheet
import com.foodandservice.util.FysBottomSheets.showGenericBottomSheet

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var allergenIntoleranceAdapter: AllergenIntoleranceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allergensAndIntolerances = listOf(
            AllergenIntolerance(name = "Celiac"),
            AllergenIntolerance(name = "Egg"),
            AllergenIntolerance(name = "Milk"),
            AllergenIntolerance(name = "Sesame"),
            AllergenIntolerance(name = "Vegetarian")
        )

        allergenIntoleranceAdapter = AllergenIntoleranceAdapter()
        allergenIntoleranceAdapter.submitList(allergensAndIntolerances)

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnShowProductIngredients.setOnClickListener {
                showGenericBottomSheet(layout = R.layout.bottom_sheet_product_ingredients)
            }

            btnShowAllergensAndIntolerances.setOnClickListener {
                showAllergiesAndIntolerancesBottomSheet(
                    layout = R.layout.bottom_sheet_product_allergies_intolerances,
                    allergenIntoleranceAdapter = allergenIntoleranceAdapter
                )
            }

            btnShowProductExtras.setOnClickListener {

            }
        }
    }
}