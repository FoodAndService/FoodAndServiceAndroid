package com.foodandservice.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentCartBinding
import com.foodandservice.domain.model.CartItem
import com.foodandservice.presentation.ui.adapter.CartAdapter


class CartFragment : Fragment(), CartAdapter.CartItemClickListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        binding.apply {
            btnPaymentProceed.setOnClickListener {

            }

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAdapter() {
        cartAdapter = CartAdapter(this)

        val cart = listOf(
            CartItem("1", "Pepsi", "", "1,99", 1, false),
            CartItem("2", "Copa de vino", "", "2,99", 1, false),
            CartItem("3", "Patatas fritas", "", "0,99", 1, true),
            CartItem("4", "Pollo frito", "", "3,99", 1, false),
            CartItem("5", "Patatas fritas", "", "0,99", 1, true),
            CartItem("6", "Patatas gajo", "", "0,99", 1, true),
        )

        cartAdapter.submitList(cart)
        binding.rvCart.adapter = cartAdapter
    }

    override fun onClickSubtractQuantity(cartItem: CartItem, position: Int) {

    }

    override fun onClickAddQuantity(cartItem: CartItem, position: Int) {

    }
}