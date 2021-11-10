package com.foodandservice.ui.login

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner, {
            when (it) {
                LoginViewModel.State.Success -> {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                LoginViewModel.State.PhoneFormatError -> {
                    binding.tilPhone.error = getString(R.string.error_phone_format)
                }
            }
        })

        binding.btnLostPhone.setOnClickListener {

        }

        binding.btnAccess.setOnClickListener {
            binding.tilPhone.isErrorEnabled = false
            viewModel.login(binding.tiePhone.text.toString())
        }

        getPhonePrefix()
    }

    private fun getPhonePrefix() {
        var countryCode = ""

        val manager =
            requireContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryId = manager.simCountryIso.toUpperCase()
        val countryCodes = requireContext().resources.getStringArray(R.array.CountryCodes)

        for (i in countryCodes.indices) {
            val entry = countryCodes[i].split(",").toTypedArray()
            if (entry[1].trim { it <= ' ' } == countryId.trim { it <= ' ' }) {
                countryCode = entry[0]
                break
            }
        }

        binding.tiePrefix.setText(("+$countryCode"))
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}