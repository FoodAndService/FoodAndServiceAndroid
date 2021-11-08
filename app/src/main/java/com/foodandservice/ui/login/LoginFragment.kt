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
import com.foodandservice.R
import com.foodandservice.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var databinding: FragmentLoginBinding
    private val viewModel: LoginViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner, {
            when (it) {
                LoginViewModel.State.Success -> {
                    makeToast("Correct")
                }
                LoginViewModel.State.PhoneFormatError -> {
                    databinding.tilPhone.error = getString(R.string.error_phone_format)
                }
            }
        })

        databinding.btnLostPhone.setOnClickListener {

        }

        databinding.btnAccess.setOnClickListener {
            databinding.tilPhone.isErrorEnabled = false
            viewModel.login(databinding.tiePhone.text.toString())
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

        databinding.tiePrefix.setText(("+$countryCode"))
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}