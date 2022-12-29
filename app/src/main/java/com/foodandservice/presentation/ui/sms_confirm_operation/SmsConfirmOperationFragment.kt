package com.foodandservice.presentation.ui.sms_confirm_operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSmsConfirmOperationBinding

class SmsConfirmOperationFragment : Fragment() {
    private lateinit var binding: FragmentSmsConfirmOperationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sms_confirm_operation,
            container,
            false
        )
        return binding.root
    }

}