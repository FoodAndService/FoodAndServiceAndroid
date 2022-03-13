package com.foodandservice.presentation.ui.sms_confirm

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSmsConfirmBinding
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmsConfirmFragment : Fragment() {
    private lateinit var binding: FragmentSmsConfirmBinding
    private lateinit var timer: CountDownTimer
    private val viewModel by viewModels<SmsConfirmViewModelImpl>()
    private val TAG = "SmsConfirmFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sms_confirm, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSms.startListeningForIncomingMessages()

        binding.btnConfirm.setOnClickListener {

        }

        binding.btnResendSms.setOnClickListener {
            initCountDownTimer()
        }

        binding.etSms.onChangeListener =
            SmsConfirmationView.OnChangeListener { code, isComplete ->

            }

        viewModel.getState().observe(viewLifecycleOwner) {
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
        }

        initCountDownTimer()
    }

    private fun initCountDownTimer() {
        binding.btnResendSms.isEnabled = false

        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                try {
                    binding.btnResendSms.text =
                        getString(R.string.btn_resend_sms_wait, (millisUntilFinished / 1000))
                } catch (e: Exception) {
                    Log.e(TAG, e.message, e)
                }
            }

            override fun onFinish() {
                try {
                    binding.btnResendSms.text =
                        getString(R.string.btn_resend_sms)
                    binding.btnResendSms.isEnabled = true
                } catch (e: Exception) {
                    Log.e(TAG, e.message, e)
                }
            }
        }

        timer.start()
    }
}