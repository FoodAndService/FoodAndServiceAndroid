package com.foodandservice.presentation.ui.order_details_current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodandservice.databinding.FragmentOrderDetailsCurrentBinding
import com.foodandservice.util.extensions.CoreExtensions.navigateBack

class OrderDetailsCurrentFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailsCurrentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsCurrentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }
        }
    }
}