package com.foodandservice.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.foodandservice.R
import com.foodandservice.databinding.FragmentViewPagerBinding
import com.foodandservice.ui.adapter.OnboardingAdapter
import com.foodandservice.ui.onboarding.screens.FirstScreen
import com.foodandservice.ui.onboarding.screens.FourthScreen
import com.foodandservice.ui.onboarding.screens.SecondScreen
import com.foodandservice.ui.onboarding.screens.ThirdScreen

class ViewPagerFragment : Fragment() {
    private lateinit var databinding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            FourthScreen(),
        )

        val adapter = OnboardingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        databinding.vpOnboarding.adapter = adapter
        return databinding.root
    }
}