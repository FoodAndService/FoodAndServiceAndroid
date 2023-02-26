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
import com.foodandservice.domain.model.CartItem
import com.foodandservice.presentation.ui.adapter.CartAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartState.collect { state ->
                    when (state) {
                        is CartState.Success -> {
                            cartAdapter.submitList(state.cartItems)
                        }
                        is CartState.Loading -> {
                            setLoadingState()
                        }
                        is CartState.Error -> {

                        }
                        is CartState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            btnPaymentProceed.setOnClickListener {

            }

            btnBack.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun setAdapter() {
        cartAdapter = CartAdapter(this).also { adapter ->
            binding.rvCart.adapter = adapter
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    override fun onClickSubtractQuantity(cartItem: CartItem, position: Int) {

    }

    override fun onClickAddQuantity(cartItem: CartItem, position: Int) {

    }
}