package com.foodandservice.presentation.ui.restaurant_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantDetailsBinding
import com.foodandservice.domain.model.CategoryWithProducts
import com.foodandservice.domain.model.Product
import com.foodandservice.presentation.ui.adapter.ProductAdapter
import com.foodandservice.util.ScheduleArrayAdapter
import com.foodandservice.util.getTabbedListMediatorIndices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailsFragment : Fragment(), ProductAdapter.ProductClickListener {
    private lateinit var binding: FragmentRestaurantDetailsBinding
    private lateinit var productAdapter: ProductAdapter
    private val viewModel by viewModels<RestaurantDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_restaurant_details,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSchedule()
        initAdapters()

        binding.btnReserve.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantInfoFragment_to_tableReservationFragment)
        }
    }

    private fun initSchedule() {
        val schedule = listOf(
            "Lunes: Cerrado\n" +
                    "Martes: 12:00 a 17:00 y 20:30 a 02:00\n" +
                    "Miércoles: 12:00 a 17:00 y 20:30 a 02:00\n" +
                    "Jueves: 12:00 a 17:00 y 20:30 a 02:00\n" +
                    "Viernes: 12:00 a 17:00 y 20:30 a 02:00\n" +
                    "Sábado: 12:00 a 17:00 y 20:30 a 02:00\n" +
                    "Domingo: 12:00 a 17:00 y 20:30 a 02:00"
        )

        val scheduleAdapter =
            ScheduleArrayAdapter(requireContext(), R.layout.item_schedule, schedule)
        binding.scheduleMenu.setAdapter(scheduleAdapter)
    }

    private fun initAdapters() {
        val categoriesWithProducts = listOf(
            CategoryWithProducts(
                "Bebidas", listOf(
                    Product(1, 1, "Pepsi", true, "2,00"),
                    Product(2, 1, "Fanta", true, "2,00"),
                    Product(3, 1, "Nestea", true, "2,50"),
                    Product(4, 1, "Pepsi Max", true, "2,90"),
                    Product(5, 1, "Lágrimas", true, "4,20"),
                )
            ),
            CategoryWithProducts(
                "Entrantes", listOf(
                    Product(6, 1, "Aceitunas verdes", false, "1,50"),
                    Product(7, 1, "Aceitunas negras", false, "1,80"),
                    Product(8, 1, "Croquetas de atún", false, "3,80"),
                    Product(9, 1, "Guacamole", false, "2,80"),
                    Product(10, 1, "Quesadillas", false, "2,20"),
                )
            ),
            CategoryWithProducts(
                "Pizzas", listOf(
                    Product(11, 1, "Pizza carbonara", false, "8,50"),
                    Product(12, 1, "Pizza barbacoa", false, "9,80"),
                    Product(13, 1, "Pizza cuatro quesos", false, "7,80"),
                    Product(14, 1, "Pizza extravaganza", false, "9,50"),
                    Product(15, 1, "Pizza pulled pork", false, "12,80"),
                )
            ),
            CategoryWithProducts(
                "Sopas", listOf(
                    Product(16, 1, "Gazpacho", false, "5,80"),
                    Product(17, 1, "Salmorejo", false, "5,90"),
                    Product(18, 1, "Sopa de fideos", false, "4,90"),
                    Product(19, 1, "Caldo de pollo", false, "3,90"),
                    Product(20, 1, "Sopa de verduras", false, "5,10"),
                )
            ),
            CategoryWithProducts(
                "Aperitivos", listOf(
                    Product(21, 1, "Patatas fritas", false, "3,10"),
                    Product(22, 1, "Patatas gajo", false, "3,10"),
                    Product(23, 1, "Nuggets de pollo", false, "4,10"),
                    Product(24, 1, "Tiras de pollo", false, "4,50"),
                    Product(25, 1, "Patatas bravas", false, "6,10"),
                )
            ),
            CategoryWithProducts(
                "Postres", listOf(
                    Product(26, 1, "Helado de fresa", false, "2,90"),
                    Product(27, 1, "Helado de chocolate", false, "3,10"),
                    Product(28, 1, "Tarta de queso", false, "2,90"),
                    Product(29, 1, "Flan", false, "2,90"),
                    Product(30, 1, "Tiramisú", false, "2,90"),
                )
            )
        )

        productAdapter = ProductAdapter(this)
        val products = mutableListOf<Product>()
        categoriesWithProducts.forEach { it -> it.products.forEach { products.add(it) } }
        productAdapter.submitList(products)
        binding.rvProduct.adapter = productAdapter

        initTabLayout(categoriesWithProducts)
    }

    private fun initTabLayout(categoriesWithProducts: List<CategoryWithProducts>) {
        categoriesWithProducts.forEach {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it.categoryName))
        }

        TabbedListMediator(
            binding.rvProduct,
            binding.tabLayout,
            getTabbedListMediatorIndices(categoriesWithProducts),
            true
        ).attach()
    }

    override fun onClick(product: Product) {

    }
}