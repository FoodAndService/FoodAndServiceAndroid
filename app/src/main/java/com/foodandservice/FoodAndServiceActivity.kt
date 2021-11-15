package com.foodandservice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.foodandservice.databinding.ActivityFoodandserviceBinding

class FoodAndServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodandserviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_foodandservice)
        binding.bottomNavView.setupWithNavController(findNavController(R.id.navHostFragment))
        setDestinationsListener()
    }

    private fun setDestinationsListener() {
        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment)
                bottomBarVisible(true)
            if (destination.id == R.id.reviewCreateFragment)
                bottomBarVisible(true)
        }
    }

    private fun bottomBarVisible(isVisible: Boolean) {
        binding.bottomAppBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.btnQrScan.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}