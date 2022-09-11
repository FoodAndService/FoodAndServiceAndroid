package com.foodandservice.presentation.ui.sms_confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSmsConfirmBinding
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SmsConfirmFragment : Fragment() {
    private lateinit var binding: FragmentSmsConfirmBinding
    private val args: SmsConfirmFragmentArgs by navArgs()
    private val viewModel by viewModels<SmsConfirmViewModel>()

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.smsConfirmState.collect { state ->
                when (state) {
                    is SmsConfirmState.SuccessNewCustomer -> navigateToSignUpFinish()
                    is SmsConfirmState.SuccessExistentCustomer -> navigateToHome()
                    is SmsConfirmState.Error -> Unit
                    is SmsConfirmState.Loading -> Unit
                    is SmsConfirmState.Idle -> Unit
                }
            }
        }

        lifecycleScope.launch {
            viewModel.countDownTimerState.collect { state ->
                binding.btnResendSms.isEnabled = state.isBtnEnabled

                binding.btnResendSms.text =
                    if (state.isBtnEnabled) getString(R.string.btn_resend_sms) else getString(
                        R.string.btn_resend_sms_wait,
                        state.time
                    )
            }
        }

        binding.btnConfirm.setOnClickListener {
            viewModel.sendSms(args.phone, binding.etSms.enteredCode)
        }

        binding.btnResendSms.setOnClickListener {
            viewModel.initCountDownTimer()
        }

        binding.etSms.onChangeListener =
            SmsConfirmationView.OnChangeListener { code, isComplete ->

            }

    }

    private fun navigateToHome() {
        val action = SmsConfirmFragmentDirections.actionSmsConfirmFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun navigateToSignUpFinish() {
        val action = SmsConfirmFragmentDirections.actionSmsConfirmFragmentToSignupFinishFragment()
        findNavController().navigate(action)
    }
}