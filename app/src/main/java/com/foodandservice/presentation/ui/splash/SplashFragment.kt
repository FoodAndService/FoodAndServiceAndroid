package com.foodandservice.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentSplashBinding
import org.koin.android.ext.android.get

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.splashState.collect { state ->
                when (state) {
                    is SplashState.LoggedIn -> findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    is SplashState.NotLoggedIn -> findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    is SplashState.OnboardingNotFinished -> findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                    is SplashState.Error -> Unit
                    is SplashState.Idle -> Unit
                }
            }
        }
    }
}