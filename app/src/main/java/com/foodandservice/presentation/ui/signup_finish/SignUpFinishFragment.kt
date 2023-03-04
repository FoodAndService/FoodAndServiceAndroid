package com.foodandservice.presentation.ui.signup_finish

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
import com.foodandservice.databinding.FragmentSignupFinishBinding
import com.foodandservice.util.extensions.CoreExtensions.hideKeyboard
import com.foodandservice.util.extensions.CoreExtensions.navigate
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class SignUpFinishFragment : Fragment() {
    private lateinit var binding: FragmentSignupFinishBinding
    private val viewModel: SignUpFinishViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpFinishState.collect { state ->
                    when (state) {
                        is SignUpFinishState.Success -> {
                            hideKeyboard()
                            navigate(SignUpFinishFragmentDirections.actionSignupFinishFragmentToHomeFragment())
                        }
                        is SignUpFinishState.Error -> {

                        }
                        is SignUpFinishState.Loading -> {
                            setLoadingState()
                        }
                        is SignUpFinishState.Idle -> {
                            setIdleState()
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
    }

    private fun setIdleState() {
        binding.apply {
            btnFinishSignup.isEnabled = true
            btnFinishSignup.text = getString(R.string.btn_finish_signup)
            progressBar.visibility = View.GONE
        }
    }

    private fun setLoadingState() {
        binding.apply {
            btnFinishSignup.isEnabled = true
            btnFinishSignup.text = ""
            progressBar.visibility = View.GONE
        }
    }
}