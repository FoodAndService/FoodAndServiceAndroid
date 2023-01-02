package com.foodandservice.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.foodandservice.databinding.FragmentSplashBinding
import com.foodandservice.util.extensions.CoreExtensions.navigate
import org.koin.android.ext.android.get

class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentSplashBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.splashState.collect { state ->
                when (state) {
                    is SplashState.UserLoggedIn -> {
                        navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                    }
                    is SplashState.UserNotLoggedIn -> {
                        navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                    }
                    is SplashState.OnboardingNotFinished -> {
                        navigate(SplashFragmentDirections.actionSplashFragmentToViewPagerFragment())
                    }
                    is SplashState.Error -> {

                    }
                    is SplashState.Idle -> {

                    }
                }
            }
        }
    }
}