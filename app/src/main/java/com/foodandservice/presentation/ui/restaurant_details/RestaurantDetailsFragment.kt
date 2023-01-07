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

    private fun setAdapter() {
        val categoriesWithProducts = listOf(
            CategoryWithProducts(
                categoryName = "Bebidas", products = listOf(
                    Product(
                        id = "1",
                        name = "Pepsi",
                        image = "1",
                        inStock = false,
                        isRefill = true,
                        price = "2,00"
                    ),
                    Product(
                        id = "2",
                        name = "Fanta",
                        image = "1",
                        inStock = true,
                        isRefill = true,
                        price = "2,00"
                    ),
                    Product(
                        id = "3",
                        name = "Nestea",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,50"
                    ),
                    Product(
                        id = "4",
                        name = "Pepsi Max",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,90"
                    ),
                    Product(
                        id = "5",
                        name = "Lágrimas",
                        image = "1",
                        inStock = false,
                        isRefill = true,
                        price = "4,20"
                    ),
                )
            ), CategoryWithProducts(
                categoryName = "Entrantes", products = listOf(
                    Product(
                        id = "6",
                        name = "Aceitunas verdes",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "1,50"
                    ),
                    Product(
                        id = "7",
                        name = "Aceitunas negras",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "1,80"
                    ),
                    Product(
                        id = "8",
                        name = "Croquetas de atún",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "3,80"
                    ),
                    Product(
                        id = "9",
                        name = "Guacamole",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,80"
                    ),
                    Product(
                        id = "10",
                        name = "Quesadillas",
                        image = "1",
                        inStock = false,
                        isRefill = false,
                        price = "2,20"
                    ),
                )
            ), CategoryWithProducts(
                categoryName = "Pizzas", products = listOf(
                    Product(
                        id = "11",
                        name = "Pizza carbonara",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "8,50"
                    ),
                    Product(
                        id = "12",
                        name = "Pizza barbacoa",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "9,80"
                    ),
                    Product(
                        id = "13",
                        name = "Pizza cuatro quesos",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "7,80"
                    ),
                    Product(
                        id = "14",
                        name = "Pizza extravaganza",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "9,50"
                    ),
                    Product(
                        id = "15",
                        name = "Pizza pulled pork",
                        image = "1",
                        inStock = false,
                        isRefill = false,
                        price = "12,80"
                    ),
                )
            ), CategoryWithProducts(
                categoryName = "Sopas", products = listOf(
                    Product(
                        id = "16",
                        name = "Gazpacho",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "5,80"
                    ),
                    Product(
                        id = "17",
                        name = "Salmorejo",
                        image = "1",
                        inStock = false,
                        isRefill = false,
                        price = "5,90"
                    ),
                    Product(
                        id = "18",
                        name = "Sopa de fideos",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "4,90"
                    ),
                    Product(
                        id = "19",
                        name = "Caldo de pollo",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "3,90"
                    ),
                    Product(
                        id = "20",
                        name = "Sopa de verduras",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "5,10"
                    ),
                )
            ), CategoryWithProducts(
                categoryName = "Aperitivos", products = listOf(
                    Product(
                        id = "21",
                        name = "Patatas fritas",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "3,10"
                    ),
                    Product(
                        id = "22",
                        name = "Patatas gajo",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "3,10"
                    ),
                    Product(
                        id = "23",
                        name = "Nuggets de pollo",
                        image = "1",
                        inStock = false,
                        isRefill = false,
                        price = "4,10"
                    ),
                    Product(
                        id = "24",
                        name = "Tiras de pollo",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "4,50"
                    ),
                    Product(
                        id = "25",
                        name = "Patatas bravas",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "6,10"
                    ),
                )
            ), CategoryWithProducts(
                categoryName = "Postres", products = listOf(
                    Product(
                        id = "26",
                        name = "Helado de fresa",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,90"
                    ), Product(
                        id = "27",
                        name = "Helado de chocolate",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "3,10"
                    ), Product(
                        id = "28",
                        name = "Tarta de queso",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,90"
                    ), Product(
                        id = "29",
                        name = "Flan",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,90"
                    ), Product(
                        id = "30",
                        name = "Tiramisú",
                        image = "1",
                        inStock = true,
                        isRefill = false,
                        price = "2,90"
                    )
                )
            )
        )
        val products = mutableListOf<Product>()
        categoriesWithProducts.forEach { it -> it.products.forEach { products.add(it) } }

        productAdapter = ProductAdapter(this).also { adapter ->
            binding.apply {
                rvProduct.adapter = adapter
                rvProduct.addItemDecoration(RecyclerViewItemDecoration(topMargin = 32))
            }
            adapter.submitList(products)
        }

        initTabLayout(categoriesWithProducts)
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