package com.foodandservice.presentation.ui.signup_finish

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
import com.foodandservice.databinding.FragmentSignupFinishBinding
import com.foodandservice.util.extensions.CoreExtensions.hideKeyboard
import org.koin.android.ext.android.get

class SignUpFinishFragment : Fragment() {
    private lateinit var binding: FragmentSignupFinishBinding
    private val viewModel: SignUpFinishViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_signup_finish, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.signUpFinishState.collect { state ->
                when (state) {
                    is SignUpFinishState.Success -> {
                        navigateToHome()
                    }
                    is SignUpFinishState.Error -> {

                    }
                    is SignUpFinishState.Loading -> {
                        binding.apply {
                            btnFinishSignup.isEnabled = true
                            btnFinishSignup.text = ""
                            progressBar.visibility = View.GONE
                        }
                    }
                    is SignUpFinishState.Idle -> {
                        binding.apply {
                            btnFinishSignup.isEnabled = true
                            btnFinishSignup.text = getString(R.string.btn_finish_signup)
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }

        binding.btnFinishSignup.setOnClickListener {
            viewModel.finishSignup(binding.tieFullname.text.toString())
        }

        binding.apply {
            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
        }
    }

    private fun navigateToHome() {
        hideKeyboard()
        val action = SignUpFinishFragmentDirections.actionSignupFinishFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}