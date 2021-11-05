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
import com.foodandservice.ui.sms_confirm.SmsConfirmViewModel

class SignupFinishFragment : Fragment() {
    private lateinit var databinding: FragmentSignupFinishBinding
    private val viewModel: SignupFinishViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup_finish, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databinding.btnFinishSignup.setOnClickListener {
            viewModel.finishSignup(databinding.tieFullname.text.toString())
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