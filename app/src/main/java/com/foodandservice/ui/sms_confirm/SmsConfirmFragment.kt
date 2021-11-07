package com.foodandservice.ui.sms_confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSmsConfirmBinding
import com.fraggjkee.smsconfirmationview.SmsConfirmationView

class SmsConfirmFragment : Fragment() {
    private lateinit var databinding: FragmentSmsConfirmBinding
    private val viewModel: SmsConfirmViewModelImpl by viewModels()
    private val TAG = "SmsConfirmFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sms_confirm, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databinding.etSms.startListeningForIncomingMessages()

        databinding.btnConfirm.setOnClickListener {

        }

        databinding.etSms.onChangeListener =
            SmsConfirmationView.OnChangeListener { code, isComplete ->

            }
        
        viewModel.getState().observe(viewLifecycleOwner, {
            when (it) {
                SmsConfirmViewModel.State.SmsFormatError -> {

                }
                SmsConfirmViewModel.State.SmsEmptyError -> {

                }
                SmsConfirmViewModel.State.SmsIncorrectError -> {

                }
                SmsConfirmViewModel.State.Success -> {

                }
            }
        })
    }
}