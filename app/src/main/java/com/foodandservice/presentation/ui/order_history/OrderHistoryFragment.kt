package com.foodandservice.presentation.ui.order_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.foodandservice.R
import com.foodandservice.databinding.FragmentOrderHistoryBinding
import com.foodandservice.domain.model.OrderHistory
import com.foodandservice.presentation.ui.adapter.OrderHistoryAdapter
import java.time.LocalDateTime

class OrderHistoryFragment : Fragment(), OrderHistoryAdapter.OrderHistoryClickListener {
    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order_history,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        val orderHistoryList = listOf(
            OrderHistory(
                restaurantName = "Wendy's",
                amount = "15,00",
                date = LocalDateTime.of(2022, 11, 20, 0, 0)
            ),
            OrderHistory(
                restaurantName = "Rosario's Burger",
                amount = "12,00",
                date = LocalDateTime.of(2022, 11, 14, 0, 0)
            ),
            OrderHistory(
                restaurantName = "Foster Hollywood",
                amount = "30,00",
                date = LocalDateTime.of(2022, 11, 11, 0, 0)
            ),
            OrderHistory(
                restaurantName = "La Calle Burger",
                amount = "17,00",
                date = LocalDateTime.of(2022, 11, 7, 0, 0)
            ),
            OrderHistory(
                restaurantName = "Kalua",
                amount = "6,00",
                date = LocalDateTime.of(2022, 10, 3, 0, 0)
            )
        )

        orderHistoryAdapter.submitList(orderHistoryList)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

        }
    }

    private fun setAdapter() {
        orderHistoryAdapter = OrderHistoryAdapter(this)
        binding.rvOrderHistory.adapter = orderHistoryAdapter
    }

    override fun onClick(item: OrderHistory) {

    }
}