package com.foodandservice.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentCartBinding
import com.foodandservice.domain.model.cart.RestaurantCart
import com.foodandservice.domain.model.cart.RestaurantCartItem
import com.foodandservice.presentation.ui.adapter.CartAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class CartFragment : Fragment(), CartAdapter.CartItemClickListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private val viewModel: CartViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewModel.getCart()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartState.collect { state ->
                    when (state) {
                        is CartState.Success -> {
                            setCartItems(restaurantCart = state.restaurantCart)
                        }

                        is CartState.Loading -> {
                            setLoadingState()
                        }

                        is CartState.Error -> {

                        }

                        is CartState.Idle -> {
                            setIdleState()
                        }

                        is CartState.Empty -> {
                            setCartEmpty()
                        }
                    }
                }
            }
        }

        binding.btnConfirmOrder.setOnClickListener {

        }
    }

    private fun setCartItems(restaurantCart: RestaurantCart) {
        cartAdapter.submitList(restaurantCart.items)
        binding.constraintCart.visibility = View.VISIBLE
    }

    private fun setAdapter() {
        cartAdapter = CartAdapter(this).also { adapter ->
            binding.rvCart.adapter = adapter
        }
    }

    private fun setCartEmpty() {
        binding.apply {
            constraintCartEmpty.visibility = View.VISIBLE
        }
    }

    private fun setLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            constraintCart.visibility = View.GONE
            constraintCartEmpty.visibility = View.GONE
        }
    }

    private fun setIdleState() {
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    override fun onClickSubtractQuantity(restaurantCartItem: RestaurantCartItem, position: Int) {

    }

    override fun onClickAddQuantity(restaurantCartItem: RestaurantCartItem, position: Int) {

    }
}