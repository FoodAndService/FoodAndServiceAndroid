package com.foodandservice

import android.os.Bundle
import android.widget.Toast
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
    }

    fun bottomBarVisibility(visibility: Int) {
        binding.bottomAppBar.visibility = visibility
        binding.btnQrScan.visibility = visibility
    }
}