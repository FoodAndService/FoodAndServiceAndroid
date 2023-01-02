package com.foodandservice.presentation.ui.sms_confirm_auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.foodandservice.R
import com.foodandservice.common.Constants
import com.foodandservice.databinding.FragmentSmsConfirmSignBinding
import com.foodandservice.util.extensions.CoreExtensions.hideKeyboard
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.showToast
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class SmsConfirmAuthFragment : Fragment() {
    private lateinit var binding: FragmentSmsConfirmSignBinding
    private val args: SmsConfirmAuthFragmentArgs by navArgs()
    private val viewModel: SmsConfirmViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmsConfirmSignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.smsConfirmAuthState.collect { state ->
                when (state) {
                    is SmsConfirmAuthState.SuccessNewCustomer -> {
                        navigate(SmsConfirmAuthFragmentDirections.actionSmsConfirmAuthFragmentToSignupFinishFragment())
                    }
                    is SmsConfirmAuthState.SuccessExistentCustomer -> {
                        hideKeyboard()
                        navigate(SmsConfirmAuthFragmentDirections.actionSmsConfirmAuthFragmentToHomeFragment())
                    }
                    is SmsConfirmAuthState.Error -> {
                        showToast(state.message)
                    }
                    is SmsConfirmAuthState.Loading -> {
                        binding.apply {
                            btnConfirm.isEnabled = false
                            btnConfirm.text = ""
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is SmsConfirmAuthState.Idle -> {
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
                binding.apply {
                    btnResendSms.isEnabled = state.isBtnEnabled
                    btnResendSms.text =
                        if (state.isBtnEnabled) getString(R.string.btn_resend_sms) else getString(
                            R.string.btn_resend_sms_wait, state.time
                        )
                }
            }
        }

        binding.apply {
            etSms.startListeningForIncomingMessages()

            btnConfirm.setOnClickListener {
                viewModel.sendSms(args.phone, binding.etSms.enteredCode)
            }

            btnResendSms.setOnClickListener {
                viewModel.initCountDownTimer()
            }

            etSms.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete -> }

            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
        }
    }
}