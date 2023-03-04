package com.foodandservice.presentation.ui.order_details_current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentOrderDetailsCurrentBinding
import com.foodandservice.presentation.ui.adapter.OrderHistoryProductAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class OrderDetailsCurrentFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailsCurrentBinding
    private lateinit var orderCurrentAdapter: OrderHistoryProductAdapter
    private val viewModel: OrderDetailsCurrentViewModel = get()

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
                viewModel.orderDetailsCurrentViewModel.collect { state ->
                    when (state) {
                        is OrderDetailsCurrentState.Success -> {
                            orderCurrentAdapter.submitList(state.order)
                        }
                        is OrderDetailsCurrentState.Loading -> {
                            setLoadingState()
                        }
                        is OrderDetailsCurrentState.Error -> {

                        }
                        is OrderDetailsCurrentState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    private fun setAdapter() {
        orderCurrentAdapter = OrderHistoryProductAdapter().also { adapter ->
            binding.rvProduct.adapter = adapter
        }
    }
}