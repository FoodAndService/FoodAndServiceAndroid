package com.foodandservice.presentation.ui.restaurant_details

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.bumptech.glide.Glide
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantDetailsBinding
import com.foodandservice.domain.model.RestaurantDetails
import com.foodandservice.domain.model.restaurant.RestaurantProduct
import com.foodandservice.domain.model.restaurant.RestaurantProductCategoryWithProducts
import com.foodandservice.presentation.ui.adapter.RestaurantProductAdapter
import com.foodandservice.util.RecyclerViewItemDecoration
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import com.foodandservice.util.getTabbedListMediatorIndices
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class RestaurantDetailsFragment : Fragment(),
    RestaurantProductAdapter.RestaurantProductClickListener {
    private lateinit var binding: FragmentRestaurantDetailsBinding
    private lateinit var restaurantProductAdapter: RestaurantProductAdapter
    private lateinit var restaurantDetails: RestaurantDetails
    private val args: RestaurantDetailsFragmentArgs by navArgs()
    private val viewModel: RestaurantDetailsViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantDetailsBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewModel.getRestaurantData(args.restaurantId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantDetailsState.collect { state ->
                    when (state) {
                        is RestaurantDetailsState.DetailsSuccess -> {
                            restaurantDetails = state.restaurantDetails
                            setRestaurantDetails()
                        }

                        is RestaurantDetailsState.ProductsWithCategoriesSuccess -> {
                            setRestaurantProductsWithCategories(state.restaurantProductCategoriesWithProducts)
                        }

                        is RestaurantDetailsState.Loading -> {
                            setLoadingState()
                        }

                        is RestaurantDetailsState.Error -> {

                        }

                        is RestaurantDetailsState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            btnReserve.setOnClickListener {
                navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToRestaurantBookingFragment())
            }

            btnMoreInfo.setOnClickListener {
                navigate(
                    RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToRestaurantDetailsExtraFragment(
                        restaurantDetails = restaurantDetails
                    )
                )
            }

            btnBack.setOnClickListener {
                navigateBack()
            }

            llRating.setOnClickListener {
                navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToRestaurantReviewsFragment())
            }

            btnCart.setOnClickListener {
                navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToCartFragment())
            }

            tvRatingText.paintFlags = tvRatingText.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    private fun setRestaurantProductsWithCategories(restaurantProductCategoriesWithProducts: List<RestaurantProductCategoryWithProducts>) {
        val products = mutableListOf<RestaurantProduct>()
        restaurantProductCategoriesWithProducts.forEach { it ->
            it.products.forEach {
                products.add(
                    it
                )
            }
        }

        restaurantProductAdapter.submitList(products)

        binding.apply {
            restaurantProductCategoriesWithProducts.forEach {
                tabLayout.addTab(tabLayout.newTab().setText(it.category))
            }

            TabbedListMediator(
                rvProduct,
                tabLayout,
                getTabbedListMediatorIndices(restaurantProductCategoriesWithProducts),
                true
            ).attach()
        }
    }

    private fun setRestaurantDetails() {
        binding.apply {
            Glide.with(this@RestaurantDetailsFragment).load(restaurantDetails.logo)
                .into(restaurantLogo)
            tvRestaurantName.text = restaurantDetails.name

            tvDescription.text = restaurantDetails.description

            getRestaurantOpeningStatus()

            if (args.restaurantDistance != 0f) {
                tvDistance.text = getString(
                    R.string.restaurant_details_distance, args.restaurantDistance
                )
            } else {
                tvDistance.apply {
                    text = getString(R.string.activate_location)
                    setTextColor(ContextCompat.getColor(binding.root.context, R.color.orange))
                }
            }
        }
    }

    private fun getRestaurantOpeningStatus() {
        if (!restaurantDetails.isOpen()) {
            binding.apply {
                cvOpeningStatus.visibility = View.VISIBLE
                tvOpeningStatus.text =
                    if (restaurantDetails.isClosed()) getString(R.string.restaurant_details_opening_status_closed)
                    else getString(R.string.restaurant_details_opening_status_vacation)
            }
        }
    }

    private fun setAdapter() {
        restaurantProductAdapter = RestaurantProductAdapter(this).also { adapter ->
            binding.apply {
                rvProduct.adapter = adapter
                rvProduct.addItemDecoration(RecyclerViewItemDecoration(topMargin = 32))
            }
        }
    }

    override fun onClick(restaurantProduct: RestaurantProduct) {
        navigate(
            RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToProductDetailsFragment(
                restaurantProduct
            )
        )
    }

    private fun setIdleState() {
        binding.apply {
            progressBar.visibility = View.INVISIBLE
            constraintRestaurantDetails.visibility = View.VISIBLE
            constraintRestaurantMenu.visibility = View.VISIBLE
        }
    }

    private fun setLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            constraintRestaurantDetails.visibility = View.GONE
            constraintRestaurantMenu.visibility = View.GONE
        }
    }
}