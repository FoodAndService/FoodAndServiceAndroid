package com.foodandservice.presentation.ui.order_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentOrderHistoryBinding
import com.foodandservice.domain.model.Order
import com.foodandservice.presentation.ui.adapter.OrderHistoryAdapter
import java.time.LocalDateTime

class OrderHistoryFragment : Fragment(), OrderHistoryAdapter.OrderClickListener {
    private lateinit var binding: FragmentOrderHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_order_history, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(OrderHistoryFragmentDirections.actionOrdersFragmentToHomeFragment())
        }
    }

    private fun setAdapter() {
        val orderLists = listOf(
            Order(
                id = "1",
                restaurantName = "Wendy's",
                amount = "15,00",
                date = LocalDateTime.of(2022, 11, 20, 0, 0)
            ), Order(
                id = "2",
                restaurantName = "Rosario's Burger",
                amount = "12,00",
                date = LocalDateTime.of(2022, 11, 14, 0, 0)
            ), Order(
                id = "3",
                restaurantName = "Foster Hollywood",
                amount = "30,00",
                date = LocalDateTime.of(2022, 11, 11, 0, 0)
            ), Order(
                id = "4",
                restaurantName = "La Calle Burger",
                amount = "17,00",
                date = LocalDateTime.of(2022, 11, 7, 0, 0)
            ), Order(
                id = "5",
                restaurantName = "Kalua",
                amount = "6,00",
                date = LocalDateTime.of(2022, 10, 3, 0, 0)
            )
        )

        OrderHistoryAdapter(this).also { adapter ->
            adapter.submitList(orderLists)
            binding.rvOrderHistory.adapter = adapter
        }
    }

    override fun onClick(item: Order) {
        OrderHistoryFragmentDirections.actionOrdersFragmentToOrderDetailsPastFragment()
            .also { action ->
                findNavController().navigate(action)
            }
    }
}