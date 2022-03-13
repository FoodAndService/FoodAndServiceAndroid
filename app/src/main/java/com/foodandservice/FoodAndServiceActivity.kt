package com.foodandservice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.foodandservice.common.Constants.Companion.BottomBarVisibleFragments
import com.foodandservice.databinding.ActivityFoodandserviceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodAndServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodandserviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_foodandservice)
        binding.bottomNavView.setupWithNavController(findNavController(R.id.navHostFragment))

        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { _, destination, _ ->
            setBottomBarVisibility(BottomBarVisibleFragments.contains(destination.id))
        }
    }

    private fun setBottomBarVisibility(isVisible: Boolean) {
        binding.bottomAppBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.btnQrScan.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}