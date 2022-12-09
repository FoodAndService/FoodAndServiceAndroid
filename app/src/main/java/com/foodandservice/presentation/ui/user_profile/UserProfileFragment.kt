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
import com.foodandservice.util.extensions.ContextExtensions.showDialog
import com.google.android.material.bottomsheet.BottomSheetDialog

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
                showChangeFullnameBottomSheet()
            }

            btnChangeEmail.setOnClickListener {
                showChangeEmailBottomSheet()
            }

            btnChangePhone.setOnClickListener {
                showChangePhoneBottomSheet()
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToHomeFragment())
            }
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

    private fun showChangeFullnameBottomSheet() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)

        layoutInflater.inflate(R.layout.bottom_sheet_save_name, null).also {
            dialog.setContentView(it)
            dialog.show()
        }
    }

    private fun showChangeEmailBottomSheet() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)

        layoutInflater.inflate(R.layout.bottom_sheet_save_email, null).also {
            dialog.setContentView(it)
            dialog.show()
        }
    }

    private fun showChangePhoneBottomSheet() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)

        layoutInflater.inflate(R.layout.bottom_sheet_save_phone, null).also {
            dialog.setContentView(it)
            dialog.show()
        }
    }
}