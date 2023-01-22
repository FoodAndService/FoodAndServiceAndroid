package com.foodandservice.presentation.ui.order_details_past

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentOrderDetailsPastBinding
import com.foodandservice.presentation.ui.adapter.OrderPastAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class OrderDetailsPastFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailsPastBinding
    private lateinit var orderPastAdapter: OrderPastAdapter
    private val viewModel: OrderDetailsPastViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsPastBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderDetailsPastViewModel.collect { state ->
                    when (state) {
                        is OrderDetailsPastState.Success -> {
                            orderPastAdapter.submitList(state.order)
                        }
                        is OrderDetailsPastState.Loading -> {
                            setLoadingState()
                        }
                        is OrderDetailsPastState.Error -> {

                        }
                        is OrderDetailsPastState.Idle -> {
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

            btnQr.setOnClickListener {

            }

            btnEmail.setOnClickListener {

            }

            tvOrderNumber.text = getString(R.string.fragment_order_details_past_title, "420")
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    private fun setAdapter() {
        orderPastAdapter = OrderPastAdapter().also { adapter ->
            binding.rvProduct.adapter = adapter
        }
    }
}