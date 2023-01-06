package com.foodandservice.presentation.ui.order_details_current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentOrderDetailsCurrentBinding
import com.foodandservice.domain.model.ProductOrderPast
import com.foodandservice.presentation.ui.adapter.OrderPastAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch

class OrderDetailsCurrentFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailsCurrentBinding
    private lateinit var orderCurrentAdapter: OrderPastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsCurrentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }

            tvOrderNumber.text = getString(R.string.current_order_status_order_number, "420")
        }
    }

    private fun setAdapter() {
        val products = listOf(
            ProductOrderPast("1", "Pepsi", "", "1,99", false),
            ProductOrderPast("2", "Copa de vino", "", "2,99", false),
            ProductOrderPast("3", "Patatas fritas", "", "0,99", true),
            ProductOrderPast("4", "Pollo frito", "", "3,99", false),
            ProductOrderPast("5", "Patatas fritas", "", "0,99", true),
            ProductOrderPast("6", "Patatas gajo", "", "0,99", true),
            ProductOrderPast("7", "Pepsi", "", "1,99", false),
            ProductOrderPast("8", "Copa de vino", "", "2,99", false),
            ProductOrderPast("9", "Patatas fritas", "", "0,99", true),
            ProductOrderPast("10", "Pollo frito", "", "3,99", false)
        )

        orderCurrentAdapter = OrderPastAdapter().also { adapter ->
            adapter.submitList(products)
            binding.rvProduct.adapter = adapter
        }
    }
}