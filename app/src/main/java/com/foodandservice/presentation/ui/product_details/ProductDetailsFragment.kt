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
import com.foodandservice.presentation.ui.adapter.ProductExtraAdapter
import com.foodandservice.presentation.ui.adapter.RestaurantProductDietaryRestrictionAdapter
import com.foodandservice.util.FysBottomSheets.showAllergensAndIntolerancesBottomSheet
import com.foodandservice.util.FysBottomSheets.showProductExtrasBottomSheet
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class ProductDetailsFragment : Fragment(), ProductExtraAdapter.ProductExtraClickListener {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var restaurantProductDietaryRestrictionAdapter: RestaurantProductDietaryRestrictionAdapter
    private lateinit var productExtraAdapter: ProductExtraAdapter
    private lateinit var restaurantProductDetails: RestaurantProductDetails
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
                            restaurantProductDetails = state.restaurantProductDetails
                            setRestaurantProductDetailsInfo()
                            handleQuantityPrice()
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
                    restaurantProductDietaryRestrictionAdapter = restaurantProductDietaryRestrictionAdapter
                )
            }

            btnShowProductExtras.setOnClickListener {
                showProductExtrasBottomSheet(
                    layout = R.layout.bottom_sheet_product_extras,
                    productExtraAdapter = productExtraAdapter
                )
            }

            btnAdd.setOnClickListener {
                if (viewModel.productQuantity.value < 100) viewModel.productQuantity.value += 1
            }

            btnSubtract.setOnClickListener {
                if (viewModel.productQuantity.value > 1) viewModel.productQuantity.value -= 1
            }
        }
    }

    private fun handleQuantityPrice() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productQuantity.collect { quantity ->
                    binding.apply {
                        btnAdd.isEnabled = quantity < 100
                        btnSubtract.isEnabled = quantity > 1
                        tvQuantity.text = quantity.toString()
                        tvPriceTotal.text = restaurantProductDetails.price.toUI(quantity)
                    }
                }
            }
        }
    }

    private fun setRestaurantProductDetailsInfo() {
        binding.apply {
            tvProductName.text = restaurantProductDetails.name
            tvProductDescription.text = restaurantProductDetails.description
            Glide.with(requireContext()).load(restaurantProductDetails.image).centerCrop()
                .into(ivProductImage)
            tvPriceSingle.text =
                getString(R.string.product_price_single, restaurantProductDetails.price.toUI())
            btnShowAllergensAndIntolerances.visibility =
                if (restaurantProductDetails.dietaryRestrictions.isNotEmpty()) View.VISIBLE else View.GONE
            btnShowProductExtras.visibility =
                if (restaurantProductDetails.extras.isNotEmpty()) View.VISIBLE else View.GONE
        }

        restaurantProductDietaryRestrictionAdapter.submitList(restaurantProductDetails.dietaryRestrictions)
    }

    private fun setAdapters() {
        restaurantProductDietaryRestrictionAdapter = RestaurantProductDietaryRestrictionAdapter()
        productExtraAdapter = ProductExtraAdapter(this)
    }

    private fun setLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            constraintProductDetails.visibility = View.GONE
            btnAddToCart.visibility = View.GONE
        }
    }

    private fun setIdleState() {
        binding.apply {
            progressBar.visibility = View.GONE
            constraintProductDetails.visibility = View.VISIBLE
            btnAddToCart.visibility = View.VISIBLE
        }
    }

    override fun onClickSubtractQuantity(productExtra: RestaurantProductExtra, position: Int) {

    }

    override fun onClickAddQuantity(productExtra: RestaurantProductExtra, position: Int) {

    }
}