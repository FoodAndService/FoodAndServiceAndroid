package com.foodandservice.presentation.ui.sms_confirm_sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.foodandservice.R
import com.foodandservice.common.Constants
import com.foodandservice.databinding.FragmentSmsConfirmSignBinding
import com.foodandservice.util.extensions.CoreExtensions.hideKeyboard
import com.foodandservice.util.extensions.CoreExtensions.showToast
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class SmsConfirmSignFragment : Fragment() {
    private lateinit var binding: FragmentSmsConfirmSignBinding
    private val args: SmsConfirmSignFragmentArgs by navArgs()
    private val viewModel: SmsConfirmViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sms_confirm_sign, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSms.startListeningForIncomingMessages()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.smsConfirmSignState.collect { state ->
                when (state) {
                    is SmsConfirmSignState.SuccessNewCustomer -> navigateToSignUpFinish()
                    is SmsConfirmSignState.SuccessExistentCustomer -> navigateToHome()
                    is SmsConfirmSignState.Error -> {
                        showToast(state.message)
                    }
                    is SmsConfirmSignState.Loading -> {
                        binding.apply {
                            btnConfirm.isEnabled = false
                            btnConfirm.text = ""
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is SmsConfirmSignState.Idle -> {
                        binding.apply {
                            btnConfirm.isEnabled = true
                            btnConfirm.text = getString(R.string.btn_confirm)
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.countDownTimerState.collect { state ->
                binding.btnResendSms.isEnabled = state.isBtnEnabled

                binding.btnResendSms.text =
                    if (state.isBtnEnabled) getString(R.string.btn_resend_sms) else getString(
                        R.string.btn_resend_sms_wait, state.time
                    )
            }
        }

        binding.btnConfirm.setOnClickListener {
            viewModel.sendSms(args.phone, binding.etSms.enteredCode)
        }

        binding.btnResendSms.setOnClickListener {
            viewModel.initCountDownTimer()
        }

        binding.etSms.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->

        }

        binding.tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
    }

    private fun navigateToHome() {
        hideKeyboard()
        val action = SmsConfirmSignFragmentDirections.actionSmsConfirmSignFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun navigateToSignUpFinish() {
        val action =
            SmsConfirmSignFragmentDirections.actionSmsConfirmSignFragmentToSignupFinishFragment()
        findNavController().navigate(action)
    }
}