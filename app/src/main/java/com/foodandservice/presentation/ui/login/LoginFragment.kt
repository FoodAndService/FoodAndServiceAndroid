package com.foodandservice.presentation.ui.login

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.common.Constants
import com.foodandservice.databinding.FragmentLoginBinding
import com.foodandservice.util.extensions.CoreExtensions.showToast
import org.koin.android.ext.android.get

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Success -> {
                        navigateToSmsConfirm(state.phone)
                    }
                    is LoginState.Error -> {
                        showToast(state.message)
                    }
                    is LoginState.Loading -> {
                        binding.apply {
                            btnAccess.isEnabled = false
                            progressBar.visibility = View.VISIBLE
                            btnAccess.text = ""
                        }
                    }
                    is LoginState.Idle -> {
                        binding.apply {
                            btnAccess.isEnabled = true
                            btnAccess.text = getString(R.string.btn_access)
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }

        binding.btnAccess.setOnClickListener {
            viewModel.login(binding.countryCodePicker.selectedCountryCodeWithPlus + binding.tiePhone.text.toString())
        }

        binding.btnLostPhone.setOnClickListener {

        }

        binding.apply {
            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL

            countryCodePicker.setTypeFace(
                Typeface.createFromAsset(
                    requireContext().assets,
                    "fonts/poppins.ttf"
                )
            )
        }
    }

    private fun navigateToSmsConfirm(phone: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToSmsConfirmFragment(phone)
        findNavController().navigate(action)
    }
}