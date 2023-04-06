package com.foodandservice.presentation.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                    if (position == onboardingItems.size - 1) lastPage() else notLastPage()
                }
            })

            btnSkip.setOnClickListener {
                viewModel.finishOnboarding()
                navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
            }

            tvCopyright.text = Constants.FYS_COPYRIGHT_LABEL
        }
    }

    private fun lastPage() {
        binding.apply {
            btnOnboarding.apply {
                text = getString(R.string.btn_finish)
                setOnClickListener {
                    viewModel.finishOnboarding()
                    navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
                }
            }
            btnSkip.visibility = View.GONE
        }
    }

    private fun notLastPage() {
        binding.apply {
            btnOnboarding.apply {
                text = getString(R.string.btn_next)
                setOnClickListener {
                    vpOnboarding.currentItem += 1
                }
            }
            btnSkip.visibility = View.VISIBLE
        }
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
            )
        )

        OnboardingAdapter(onboardingItems).also { adapter ->
            binding.vpOnboarding.adapter = adapter
        }
    }
}