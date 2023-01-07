package com.foodandservice.presentation.ui.login

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.common.Constants
import com.foodandservice.databinding.FragmentLoginBinding
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.showToast
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect { state ->
                    when (state) {
                        is LoginState.Success -> {
                            navigate(
                                LoginFragmentDirections.actionLoginFragmentToSmsConfirmAuthFragment(
                                    state.phone
                                )
                            )
                        }
                        is LoginState.Loading -> {
                            setLoadingState()
                        }
                        is LoginState.Error -> {
                            showToast(state.message)
                        }
                        is LoginState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            countryCodePicker.setTypeFace(
                Typeface.createFromAsset(
                    requireContext().assets, "fonts/poppins.ttf"
                )
            )

            btnAccess.setOnClickListener {
                viewModel.login(binding.countryCodePicker.selectedCountryCodeWithPlus + binding.tiePhone.text.toString())
            }

            btnLostPhone.setOnClickListener {

            }

            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
        }
    }

    private fun setIdleState() {
        binding.apply {
            btnAccess.isEnabled = true
            btnAccess.text = getString(R.string.btn_access)
            progressBar.visibility = View.GONE
        }
    }

    private fun setLoadingState() {
        binding.apply {
            btnAccess.isEnabled = false
            btnAccess.text = ""
            progressBar.visibility = View.VISIBLE
        }
    }
}