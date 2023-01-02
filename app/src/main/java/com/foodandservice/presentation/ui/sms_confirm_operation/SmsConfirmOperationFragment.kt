package com.foodandservice.presentation.ui.sms_confirm_operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodandservice.databinding.FragmentSmsConfirmOperationBinding

class SmsConfirmOperationFragment : Fragment() {
    private lateinit var binding: FragmentSmsConfirmOperationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmsConfirmOperationBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

}