package com.foodandservice.presentation.ui.onboarding

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.foodandservice.R
import com.foodandservice.common.Constants
import com.foodandservice.databinding.FragmentOnboardingBinding
import com.foodandservice.domain.model.OnboardingItem
import com.foodandservice.presentation.ui.adapter.OnboardingAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigate
import org.koin.android.ext.android.get

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var onboardingItems: List<OnboardingItem>
    private val viewModel: OnboardingViewModel = get()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            locationPermissionGranted()
        } else {
            locationPermissionDenied()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        binding.apply {
            dotsIndicator.setViewPager2(vpOnboarding)

            vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    when {
                        position == onboardingItems.size - 1 -> {
                            setLastPageButtons()
                            setMainButtonToFinishOnboarding()
                        }
                        position == onboardingItems.size - 2 -> {
                            unSetLastPageButtons()
                            if (!hasLocationPermission())
                                setMainButtonToRequestLocationPermission()
                            else
                                setMainButtonToFinishOnboarding()
                        }
                        position < onboardingItems.size - 2 -> {
                            unSetLastPageButtons()
                            setMainButtonToFinishOnboarding()
                        }
                    }
                }
            })

            btnSkip.setOnClickListener {
                requestLocationPermission()
            }

            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
        }
    }

    private fun setLastPageButtons() {
        binding.apply {
            btnOnboarding.text = getString(R.string.btn_finish)
            btnSkip.visibility = View.GONE
        }
    }

    private fun unSetLastPageButtons() {
        binding.apply {
            btnOnboarding.text = getString(R.string.btn_next)
            btnSkip.visibility = View.VISIBLE
        }
    }

    private fun setMainButtonToRequestLocationPermission() {
        binding.btnOnboarding.setOnClickListener {
            requestLocationPermission()
        }
    }

    private fun setMainButtonToFinishOnboarding() {
        binding.apply {
            btnOnboarding.setOnClickListener {
                if (btnOnboarding.text == getString(R.string.btn_finish)) {
                    viewModel.finishOnboarding()
                    navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
                } else nextOnboardingPage()
            }
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
    }

    private fun nextOnboardingPage() {
        binding.vpOnboarding.currentItem += 1
    }

    private fun locationPermissionGranted() {
        viewModel.finishOnboarding()
        navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
    }

    private fun locationPermissionDenied() {
        nextOnboardingPage()
    }

    private fun setAdapter() {
        onboardingItems = listOf(
            OnboardingItem(
                title = getString(R.string.onboarding_title1),
                description = getString(R.string.onboarding_desc1)
            ), OnboardingItem(
                title = getString(R.string.onboarding_title2),
                description = getString(R.string.onboarding_desc2)
            ), OnboardingItem(
                title = getString(R.string.onboarding_title3),
                description = getString(R.string.onboarding_desc3)
            ), OnboardingItem(
                title = getString(R.string.onboarding_title4),
                description = getString(R.string.onboarding_desc4)
            ), OnboardingItem(
                title = getString(R.string.onboarding_title5),
                description = getString(R.string.onboarding_desc5)
            ), OnboardingItem(
                title = getString(R.string.onboarding_title6),
                description = getString(R.string.onboarding_desc6)
            )
        )

        OnboardingAdapter(onboardingItems).also { adapter ->
            binding.vpOnboarding.adapter = adapter
        }
    }
}