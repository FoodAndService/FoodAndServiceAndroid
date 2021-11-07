package com.foodandservice.ui.onboarding

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
import com.foodandservice.data.model.OnboardingItem
import com.foodandservice.data.preferences.ClientPreferences
import com.foodandservice.databinding.FragmentViewPagerBinding
import com.foodandservice.ui.adapter.OnboardingAdapter

class OnboardingFragment : Fragment() {
    private lateinit var databinding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding =
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

        databinding.vpOnboarding.adapter = adapter
        databinding.dotsIndicator.setViewPager2(databinding.vpOnboarding)

        databinding.vpOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == onboardingItems.size - 1)
                    databinding.btnOnboarding.text = getString(R.string.btn_finish)
                else
                    databinding.btnOnboarding.text = getString(R.string.btn_next)
            }
        })

        databinding.btnOnboarding.setOnClickListener {
            if (databinding.btnOnboarding.text == getString(R.string.btn_finish)) {
                ClientPreferences.finishOnboarding()
                findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
            } else
                databinding.vpOnboarding.currentItem += 1
        }

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        return databinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        );
    }
}