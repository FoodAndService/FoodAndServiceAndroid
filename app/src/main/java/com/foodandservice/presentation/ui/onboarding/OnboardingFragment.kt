package com.foodandservice.presentation.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.foodandservice.R
import com.foodandservice.databinding.FragmentViewPagerBinding
import com.foodandservice.presentation.ui.adapter.OnboardingAdapter
import com.foodandservice.util.FysPreferences
import com.foodandservice.domain.model.OnboardingItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)

        val onboardingItems = listOf(
            OnboardingItem(
                R.drawable.shape_onboarding,
                getString(R.string.onboarding_title1),
                getString(R.string.onboarding_description1)
            ),
            OnboardingItem(
                R.drawable.shape_onboarding,
                getString(R.string.onboarding_title2),
                getString(R.string.onboarding_description2)
            ),
            OnboardingItem(
                R.drawable.shape_onboarding,
                getString(R.string.onboarding_title3),
                getString(R.string.onboarding_description3)
            ),
            OnboardingItem(
                R.drawable.shape_onboarding,
                getString(R.string.onboarding_title4),
                getString(R.string.onboarding_description4)
            ),
            OnboardingItem(
                R.drawable.shape_onboarding,
                getString(R.string.onboarding_title5),
                getString(R.string.onboarding_description5)
            )
        )

        val adapter = OnboardingAdapter(onboardingItems)

        binding.vpOnboarding.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.vpOnboarding)

        binding.vpOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == onboardingItems.size - 1)
                    binding.btnOnboarding.text = getString(R.string.btn_finish)
                else
                    binding.btnOnboarding.text = getString(R.string.btn_next)
            }
        })

        binding.btnOnboarding.setOnClickListener {
            if (binding.btnOnboarding.text == getString(R.string.btn_finish)) {
                FysPreferences.finishOnboarding()
                findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
            } else
                binding.vpOnboarding.currentItem += 1
        }

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    }
}