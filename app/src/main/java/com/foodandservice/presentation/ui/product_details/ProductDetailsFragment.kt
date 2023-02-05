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
import com.foodandservice.domain.model.ProductExtra
import com.foodandservice.presentation.ui.adapter.AllergenIntoleranceAdapter
import com.foodandservice.presentation.ui.adapter.ProductExtraAdapter
import com.foodandservice.util.FysBottomSheets.showAllergensAndIntolerancesBottomSheet
import com.foodandservice.util.FysBottomSheets.showGenericBottomSheet
import com.foodandservice.util.FysBottomSheets.showProductExtrasBottomSheet
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class ProductDetailsFragment : Fragment(), ProductExtraAdapter.ProductExtraClickListener {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var allergenIntoleranceAdapter: AllergenIntoleranceAdapter
    private lateinit var productExtraAdapter: ProductExtraAdapter
    private var productExtras = mutableListOf<ProductExtra>()
    private val viewModel: ProductDetailsViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productDetailsState.collect { state ->
                    when (state) {
                        is ProductDetailsState.Success -> {
                            allergenIntoleranceAdapter.submitList(state.productDetails.allergensAndIntolerances)
                            productExtraAdapter.submitList(state.productDetails.productExtras)
                        }
                        is ProductDetailsState.Loading -> {
                            setLoadingState()
                        }
                        is ProductDetailsState.Error -> {

                        }
                        is ProductDetailsState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

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

    private fun setAdapters() {
        allergenIntoleranceAdapter = AllergenIntoleranceAdapter()
        productExtraAdapter = ProductExtraAdapter(this)
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

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