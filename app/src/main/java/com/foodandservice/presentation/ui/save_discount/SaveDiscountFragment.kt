package com.foodandservice.presentation.ui.save_discount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodandservice.databinding.FragmentSaveDiscountBinding

class SaveDiscountFragment : Fragment() {
    private lateinit var binding: FragmentSaveDiscountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveDiscountBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

}