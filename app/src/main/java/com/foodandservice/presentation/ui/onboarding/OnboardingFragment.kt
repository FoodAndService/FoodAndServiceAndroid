package com.foodandservice.presentation.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.foodandservice.R
import com.foodandservice.common.Constants
import com.foodandservice.databinding.FragmentViewPagerBinding
import com.foodandservice.domain.model.OnboardingItem
import com.foodandservice.presentation.ui.adapter.OnboardingAdapter
import org.koin.android.ext.android.get

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var onboardingAdapter: OnboardingAdapter
    private val viewModel: OnboardingViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapters()

        binding.btnOnboarding.setOnClickListener {
            if (binding.btnOnboarding.text == getString(R.string.btn_finish)) {
                viewModel.finishOnboarding()
                navigateToLogin()
            } else
                binding.vpOnboarding.currentItem += 1
        }
    }

    private fun setUpAdapters() {
        val onboardingItems = listOf(
            OnboardingItem(
                title = getString(R.string.onboarding_title1),
                description = getString(R.string.onboarding_desc1)
            ),
            OnboardingItem(
                title = getString(R.string.onboarding_title2),
                description = getString(R.string.onboarding_desc2)
            ),
            OnboardingItem(
                title = getString(R.string.onboarding_title3),
                description = getString(R.string.onboarding_desc3)
            ),
            OnboardingItem(
                title = getString(R.string.onboarding_title4),
                description = getString(R.string.onboarding_desc4)
            ),
            OnboardingItem(
                title = getString(R.string.onboarding_title5),
                description = getString(R.string.onboarding_desc5)
            )
        )
        onboardingAdapter = OnboardingAdapter(onboardingItems)

        binding.apply {
            vpOnboarding.adapter = onboardingAdapter

            dotsIndicator.setViewPager2(binding.vpOnboarding)

            vpOnboarding.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == onboardingItems.size - 1)
                        binding.btnOnboarding.text = getString(R.string.btn_finish)
                    else
                        binding.btnOnboarding.text = getString(R.string.btn_next)
                }
            })

            btnSkip.setOnClickListener {
                navigateToLogin()
            }

            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
    }
}