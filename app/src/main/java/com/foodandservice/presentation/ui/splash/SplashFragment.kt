package com.foodandservice.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentSplashBinding
import com.foodandservice.util.extensions.CoreExtensions.navigate
import kotlinx.coroutines.launch
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashState.collect { state ->
                    when (state) {
                        is SplashState.UserLoggedIn -> {
                            navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                        }

                        is SplashState.UserNotLoggedIn -> {
                            navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                        }

                        is SplashState.OnboardingNotFinished -> {
                            navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
                        }

                        is SplashState.Idle -> Unit
                    }
                }
            }
        }
    }
}