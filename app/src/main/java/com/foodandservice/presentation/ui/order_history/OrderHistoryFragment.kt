package com.foodandservice.presentation.ui.order_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentOrderHistoryBinding
import com.foodandservice.domain.model.Order
import com.foodandservice.presentation.ui.adapter.OrderHistoryAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigate
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class OrderHistoryFragment : Fragment(), OrderHistoryAdapter.OrderClickListener {
    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    private val viewModel: OrderHistoryViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderHistoryBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderHistoryState.collect { state ->
                    when (state) {
                        is OrderHistoryState.Success -> {
                            orderHistoryAdapter.submitList(state.orders)
                        }
                        is OrderHistoryState.Loading -> {
                            setLoadingState()
                        }
                        is OrderHistoryState.Error -> {

                        }
                        is OrderHistoryState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            root.findViewById<ConstraintLayout>(R.id.btnGoToCurrentOrder).setOnClickListener {
                navigate(OrderHistoryFragmentDirections.actionOrdersFragmentToOrderDetailsCurrentFragment())
            }
            root.findViewById<TextView>(R.id.tvCurrentOrderRestaurantName).text = "Wendy's"
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigate(OrderHistoryFragmentDirections.actionOrdersFragmentToHomeFragment())
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    private fun setAdapter() {
        orderHistoryAdapter = OrderHistoryAdapter(this).also { adapter ->
            binding.rvOrderHistory.adapter = adapter
        }
    }

    override fun onClick(item: Order) {
        navigate(OrderHistoryFragmentDirections.actionOrdersFragmentToOrderDetailsPastFragment())
    }
}