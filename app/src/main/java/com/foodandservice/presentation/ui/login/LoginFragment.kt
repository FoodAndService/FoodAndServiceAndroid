package com.foodandservice.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPhonePrefix(requireContext())

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Success -> {
                        navigateToSmsConfirm(state.phone)
                    }
                    is LoginState.Error -> {
                        makeToast(state.message)
                    }
                    is LoginState.LoadPhonePrefix -> {
                        binding.tiePrefix.setText(state.prefix)
                    }
                    is LoginState.Loading -> {
                        TODO("Loading effect")
                    }
                    is LoginState.Empty -> {}
                }
            }
        }

        binding.btnLostPhone.setOnClickListener {

        }

        binding.btnAccess.setOnClickListener {
            viewModel.login(binding.tiePrefix.text.toString() + binding.tiePhone.text.toString())
        }
    }

    private fun navigateToSmsConfirm(phone: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToSmsConfirmFragment(phone)
        findNavController().navigate(action)
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}