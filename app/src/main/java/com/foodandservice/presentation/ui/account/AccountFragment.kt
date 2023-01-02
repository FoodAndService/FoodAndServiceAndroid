package com.foodandservice.presentation.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.foodandservice.R
import com.foodandservice.databinding.FragmentAccountBinding
import com.foodandservice.util.FysBottomSheets.showAccountSettingsBottomSheet
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.showDialog
import com.foodandservice.util.extensions.CoreExtensions.showToast

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnDeleteAccount.setOnClickListener {
                showDeleteAccountDialog()
            }

            btnChangeFullname.setOnClickListener {
                showAccountSettingsBottomSheet(
                    layout = R.layout.bottom_sheet_change_name,
                    onBtnActionClick = {
                        showToast("Name saved")
                    })
            }

            btnChangeEmail.setOnClickListener {
                showAccountSettingsBottomSheet(
                    layout = R.layout.bottom_sheet_change_email,
                    onBtnActionClick = {
                        showToast("Email saved")
                    })
            }

            btnChangePhone.setOnClickListener {
                showAccountSettingsBottomSheet(
                    layout = R.layout.bottom_sheet_change_phone,
                    onBtnActionClick = {
                        showToast("Phone saved")
                    })
            }

            btnMyBookings.setOnClickListener {
                navigate(AccountFragmentDirections.actionAccountFragmentToBookingsFragment())
            }

            btnLogout.setOnClickListener {
                showLogOutDialog()
            }

            tvUserGreeting.text = getString(R.string.fragment_user_profile_greeting, "Eugenio")
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigate(AccountFragmentDirections.actionAccountFragmentToHomeFragment())
        }
    }

    private fun showLogOutDialog() {
        showDialog(title = getString(R.string.dialog_logout_title),
            description = getString(R.string.dialog_logout_desc),
            btnPositiveLabel = getString(R.string.btn_confirm),
            btnNegativeLabel = getString(R.string.btn_cancel),
            onBtnPositiveClick = {

            },
            onBtnNegativeClick = {

            })
    }

    private fun showDeleteAccountDialog() {
        showDialog(title = getString(R.string.dialog_delete_account_title),
            description = getString(R.string.dialog_delete_account_desc),
            btnPositiveLabel = getString(R.string.btn_confirm),
            btnNegativeLabel = getString(R.string.btn_cancel),
            onBtnPositiveClick = {

            },
            onBtnNegativeClick = {

            })
    }
}