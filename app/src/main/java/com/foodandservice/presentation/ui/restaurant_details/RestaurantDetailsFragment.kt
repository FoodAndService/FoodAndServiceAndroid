package com.foodandservice.presentation.ui.restaurant_details

import android.graphics.Paint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.birjuvachhani.locus.Locus
import com.bumptech.glide.Glide
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantDetailsBinding
import com.foodandservice.domain.model.CategoryWithProducts
import com.foodandservice.domain.model.Product
import com.foodandservice.domain.model.RestaurantDetails
import com.foodandservice.presentation.ui.adapter.ProductAdapter
import com.foodandservice.util.PermissionsUtils
import com.foodandservice.util.RecyclerViewItemDecoration
import com.foodandservice.util.extensions.CoreExtensions.isGPSEnabled
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import com.foodandservice.util.getTabbedListMediatorIndices
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class RestaurantDetailsFragment : Fragment(), ProductAdapter.ProductClickListener {
    private lateinit var binding: FragmentRestaurantDetailsBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var restaurantDetails: RestaurantDetails
    private val viewModel: RestaurantDetailsViewModel by lazy {
        get { parametersOf(RestaurantDetailsFragmentArgs.fromBundle(requireArguments()).restaurantId) }
    }

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantDetailsState.collect { state ->
                    when (state) {
                        is RestaurantDetailsState.Success -> {
                            restaurantDetails = state.restaurantDetails
                            setRestaurantInfo()
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
                navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToRestaurantDetailsExtraFragment())
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

    private fun setRestaurantInfo() {
        binding.apply {
            Glide.with(this@RestaurantDetailsFragment).load(restaurantDetails.logo)
                .into(restaurantLogo)
            tvRestaurantName.text = restaurantDetails.name

            tvDescription.text = restaurantDetails.description

            getRestaurantOpeningStatus()

            if (isGPSEnabled() && PermissionsUtils.hasLocationPermission(requireContext())) {
                getUserCurrentLocation()
            } else {
                tvDistance.apply {
                    text = getString(R.string.activate_location)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
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

    private fun getUserCurrentLocation() {
        Locus.getCurrentLocation(requireContext(), onResult = {
            getDistanceInKm(it.location)
        })
    }

    private fun getDistanceInKm(location: Location?) {
        val restaurantCoord = Location("Restaurant coordinates")
        val userCoord = Location("User coordinates")

        restaurantCoord.apply {
            latitude = restaurantDetails.address.latitude
            longitude = restaurantDetails.address.longitude
        }

        location?.let {
            userCoord.apply {
                latitude = it.latitude
                longitude = it.longitude
            }
        }

        val distance = userCoord.distanceTo(restaurantCoord) / 1000

        binding.tvDistance.text = getString(
            R.string.restaurant_details_distance, distance
        )
    }


    private fun initProducts(categoriesWithProducts: List<CategoryWithProducts>) {
        val products = mutableListOf<Product>()
        categoriesWithProducts.forEach { it -> it.products.forEach { products.add(it) } }

        productAdapter.submitList(products)
        initTabLayout(categoriesWithProducts)
    }

    private fun setAdapter() {
        productAdapter = ProductAdapter(this).also { adapter ->
            binding.apply {
                rvProduct.adapter = adapter
                rvProduct.addItemDecoration(RecyclerViewItemDecoration(topMargin = 32))
            }
        }
    }

    private fun initTabLayout(categoriesWithProducts: List<CategoryWithProducts>) {
        binding.apply {
            categoriesWithProducts.forEach {
                tabLayout.addTab(tabLayout.newTab().setText(it.categoryName))
            }

            TabbedListMediator(
                rvProduct, tabLayout, getTabbedListMediatorIndices(categoriesWithProducts), true
            ).attach()
        }
    }

    override fun onClick(product: Product) {
        navigate(
            RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToProductDetailsFragment(
                product
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