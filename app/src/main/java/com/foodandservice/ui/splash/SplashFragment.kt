package com.foodandservice.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner, {
            when (it) {
                //SplashViewModel.State.LoggedIn -> findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                SplashViewModel.State.NotLoggedIn -> findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                SplashViewModel.State.OnboardingNotFinished -> findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                SplashViewModel.State.NetworkError -> Toast.makeText(
                    requireContext(),
                    getString(R.string.error_network),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {}
            }
        })
    }
}