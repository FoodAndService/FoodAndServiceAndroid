package com.foodandservice.ui.signup_finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSignupFinishBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFinishFragment : Fragment() {
    private lateinit var binding: FragmentSignupFinishBinding
    private val viewModel by viewModels<SignupFinishViewModelImpl>()

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

        viewModel.getState().observe(viewLifecycleOwner, {
            when (it) {
                SignupFinishViewModel.State.NameFormatError -> {

                }
                SignupFinishViewModel.State.NameEmptyError -> {

                }
                SignupFinishViewModel.State.Success -> TODO("Confirm")
            }
        })
    }
}