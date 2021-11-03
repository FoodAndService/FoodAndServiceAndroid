package com.foodandservice.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.foodandservice.R
import com.foodandservice.data.preferences.ClientPreferences
import com.foodandservice.databinding.FragmentFourthScreenBinding

class FourthScreen : Fragment() {
    private lateinit var databinding: FragmentFourthScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fourth_screen, container, false)

        val vpOnboarding = activity?.findViewById<ViewPager2>(R.id.vpOnboarding)

        databinding.btnOnboardingFinish.setOnClickListener {
            TODO("Navigate login")
        }

        ClientPreferences.saveOnboardingStatus(true)

        return databinding.root
    }
}