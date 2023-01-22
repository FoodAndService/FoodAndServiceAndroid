package com.foodandservice.presentation.ui.order_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentOrderStatusBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class OrderStatusFragment : Fragment() {
    private lateinit var binding: FragmentOrderStatusBinding
    private val viewModel: OrderStatusViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentOrderStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderStatusState.collect { state ->
                    when (state) {
                        is OrderStatusState.Success -> {
                            binding.tvOrderStatus.text = state.orderStatus
                        }
                        is OrderStatusState.Loading -> {
                            setLoadingState()
                        }
                        is OrderStatusState.Error -> {

                        }
                        is OrderStatusState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }
}