package com.foodandservice.presentation.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentUserProfileBinding
import com.foodandservice.util.extensions.CoreExtensions.showAccountSettingsBottomSheet
import com.foodandservice.util.extensions.CoreExtensions.showDialog
import com.foodandservice.util.extensions.CoreExtensions.showToast

class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false)
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
                    }
                )
            }

            btnChangeEmail.setOnClickListener {
                showAccountSettingsBottomSheet(
                    layout = R.layout.bottom_sheet_change_email,
                    onBtnActionClick = {
                        showToast("Email saved")
                    }
                )
            }

            btnChangePhone.setOnClickListener {
                showAccountSettingsBottomSheet(
                    layout = R.layout.bottom_sheet_change_phone,
                    onBtnActionClick = {
                        showToast("Phone saved")
                    }
                )
            }

            btnLogout.setOnClickListener {
                showLogOutDialog()
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToHomeFragment())
            }
    }

    private fun showLogOutDialog() {
        showDialog(
            title = getString(R.string.dialog_logout_title),
            description = getString(R.string.dialog_logout_desc),
            btnPositiveLabel = getString(R.string.btn_confirm),
            btnNegativeLabel = getString(R.string.btn_cancel),
            onBtnPositiveClick = {

            },
            onBtnNegativeClick = {

            }
        )
    }

    private fun showDeleteAccountDialog() {
        showDialog(
            title = getString(R.string.dialog_delete_account_title),
            description = getString(R.string.dialog_delete_account_desc),
            btnPositiveLabel = getString(R.string.btn_confirm),
            btnNegativeLabel = getString(R.string.btn_cancel),
            onBtnPositiveClick = {

            },
            onBtnNegativeClick = {

            }
        )
    }
}