package com.foodandservice.presentation.ui.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.foodandservice.R
import com.foodandservice.databinding.FragmentProductDetailsBinding
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductExtra
import com.foodandservice.domain.model.restaurant_details.toUI
import com.foodandservice.presentation.ui.adapter.AllergenIntoleranceAdapter
import com.foodandservice.presentation.ui.adapter.ProductExtraAdapter
import com.foodandservice.util.FysBottomSheets.showAllergensAndIntolerancesBottomSheet
import com.foodandservice.util.FysBottomSheets.showProductExtrasBottomSheet
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class ProductDetailsFragment : Fragment(), ProductExtraAdapter.ProductExtraClickListener {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var allergenIntoleranceAdapter: AllergenIntoleranceAdapter
    private lateinit var productExtraAdapter: ProductExtraAdapter
    private val args: ProductDetailsFragmentArgs by navArgs()
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
        viewModel.getRestaurantProductDetails(
            restaurantId = args.restaurantId, productId = args.productId
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productDetailsState.collect { state ->
                    when (state) {
                        is ProductDetailsState.Success -> {
                            setRestaurantProductDetailsInfo(state.restaurantProductDetails)
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

    private fun setRestaurantProductDetailsInfo(restaurantProductDetails: RestaurantProductDetails) {
        binding.apply {
            tvProductName.text = restaurantProductDetails.name
            tvProductDescription.text = restaurantProductDetails.description
            Glide.with(requireContext()).load(restaurantProductDetails.image).centerCrop()
                .into(ivProductImage)
            tvPriceSingle.text = restaurantProductDetails.price.toUI()
        }

        allergenIntoleranceAdapter.submitList(restaurantProductDetails.allergensAndIntolerances)
    }

    private fun setAdapters() {
        allergenIntoleranceAdapter = AllergenIntoleranceAdapter()
        productExtraAdapter = ProductExtraAdapter(this)
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    override fun onClickSubtractQuantity(productExtra: RestaurantProductExtra, position: Int) {

    }

    override fun onClickAddQuantity(productExtra: RestaurantProductExtra, position: Int) {

    }
}