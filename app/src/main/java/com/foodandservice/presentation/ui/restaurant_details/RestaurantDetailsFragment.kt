package com.foodandservice.presentation.ui.restaurant_details

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.foodandservice.databinding.FragmentRestaurantDetailsBinding
import com.foodandservice.domain.model.CategoryWithProducts
import com.foodandservice.domain.model.Product
import com.foodandservice.presentation.ui.adapter.ProductAdapter
import com.foodandservice.util.RecyclerViewItemDecoration
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import com.foodandservice.util.getTabbedListMediatorIndices
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class RestaurantDetailsFragment : Fragment(), ProductAdapter.ProductClickListener {
    private lateinit var binding: FragmentRestaurantDetailsBinding
    private lateinit var productAdapter: ProductAdapter
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantDetailsState.collect { state ->
                    when (state) {
                        is RestaurantDetailsState.Success -> {
                            initProducts(state.restaurantDetails.categoriesWithProducts)
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

    }

    private fun setLoadingState() {

    }
}