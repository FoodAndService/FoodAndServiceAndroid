package com.foodandservice.presentation.ui.order_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodandservice.databinding.FragmentOrderStatusBinding

class OrderStatusFragment : Fragment() {
    private lateinit var binding: FragmentOrderStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentOrderStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

}