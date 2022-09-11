package com.foodandservice.presentation.ui.signup_finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSignupFinishBinding
import com.foodandservice.presentation.ui.sms_confirm.SmsConfirmFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFinishFragment : Fragment() {
    private lateinit var binding: FragmentSignupFinishBinding
    private val viewModel by viewModels<SignUpFinishViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_signup_finish, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFinishSignup.setOnClickListener {
            viewModel.finishSignup(binding.tieFullname.text.toString())
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.signUpFinishState.collect { state ->
                when (state) {
                    is SignUpFinishState.Success -> navigateToHome()
                    is SignUpFinishState.Error -> Unit
                    is SignUpFinishState.Loading -> Unit
                    is SignUpFinishState.Idle -> Unit
                }
            }
        }
    }

    private fun navigateToHome() {
        val action = SignUpFinishFragmentDirections.actionSignupFinishFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}