package com.foodandservice.presentation.ui.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentProductDetailsBinding
import com.foodandservice.domain.model.AllergenIntolerance
import com.foodandservice.domain.model.ProductExtra
import com.foodandservice.presentation.ui.adapter.AllergenIntoleranceAdapter
import com.foodandservice.presentation.ui.adapter.ProductExtraAdapter
import com.foodandservice.util.FysBottomSheets.showAllergensAndIntolerancesBottomSheet
import com.foodandservice.util.FysBottomSheets.showGenericBottomSheet
import com.foodandservice.util.FysBottomSheets.showProductExtrasBottomSheet
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment(), ProductExtraAdapter.ProductExtraClickListener {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var allergenIntoleranceAdapter: AllergenIntoleranceAdapter
    private lateinit var productExtraAdapter: ProductExtraAdapter
    private var productExtras = mutableListOf<ProductExtra>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        val allergensAndIntolerances = listOf(
            AllergenIntolerance(id = "1", name = "Celiac"),
            AllergenIntolerance(id = "2", name = "Egg"),
            AllergenIntolerance(id = "3", name = "Milk"),
            AllergenIntolerance(id = "4", name = "Sesame"),
            AllergenIntolerance(id = "5", name = "Vegetarian")
        )

        productExtras = mutableListOf(
            ProductExtra(
                id = "1", name = "Polla en vinagre", price = "69,00"
            ), ProductExtra(
                id = "2", name = "Patatas gajo", price = "2,25"
            ), ProductExtra(
                id = "3", name = "Batatas asadas", price = "3,00"
            ), ProductExtra(
                id = "4", name = "Huevo frito", price = "2,50"
            ), ProductExtra(
                id = "5", name = "Alcohol etílico", price = "1,00"
            ), ProductExtra(
                id = "6", name = "Chicle", price = "55,00"
            ), ProductExtra(
                id = "7", name = "Pringles", price = "2,00"
            ), ProductExtra(
                id = "8", name = "Agua", price = "2,25"
            ), ProductExtra(
                id = "9", name = "Snickers", price = "3,00"
            ), ProductExtra(
                id = "10", name = "Trembolona", price = "2,50"
            )
        )

        allergenIntoleranceAdapter = AllergenIntoleranceAdapter().also { adapter ->
            adapter.submitList(allergensAndIntolerances)
        }

        productExtraAdapter =
            ProductExtraAdapter(this).also { adapter -> adapter.submitList(productExtras) }

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }

            btnShowProductIngredients.setOnClickListener {
                showGenericBottomSheet(layout = R.layout.bottom_sheet_product_ingredients)
            }

            btnShowAllergensAndIntolerances.setOnClickListener {
                showAllergensAndIntolerancesBottomSheet(
                    layout = R.layout.bottom_sheet_product_allergens_intolerances,
                    allergenIntoleranceAdapter = allergenIntoleranceAdapter
                )
            }

            btnShowProductExtras.setOnClickListener {
                showProductExtrasBottomSheet(
                    layout = R.layout.bottom_sheet_product_extras,
                    productExtraAdapter = productExtraAdapter
                )
            }
        }
    }

    private fun updateExtras(newProductExtra: ProductExtra, position: Int) {
        productExtras.set(index = position, element = newProductExtra)
        productExtraAdapter.apply {
            submitList(productExtras)
            notifyItemChanged(position)
        }
    }

    override fun onClickSubtractQuantity(productExtra: ProductExtra, position: Int) {
        if (productExtra.quantity > 0) {
            ProductExtra(
                id = productExtra.id,
                name = productExtra.name,
                price = productExtra.price,
                quantity = productExtra.quantity - 1
            ).also { newProductExtra ->
                updateExtras(
                    newProductExtra = newProductExtra, position = position
                )
            }
        }
    }

    override fun onClickAddQuantity(productExtra: ProductExtra, position: Int) {
        if (productExtra.quantity < 100) {
            ProductExtra(
                id = productExtra.id,
                name = productExtra.name,
                price = productExtra.price,
                quantity = productExtra.quantity + 1
            ).also { newProductExtra ->
                updateExtras(
                    newProductExtra = newProductExtra, position = position
                )
            }
        }
    }
}