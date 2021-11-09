package com.foodandservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.foodandservice.databinding.ActivityFoodandserviceBinding

class FoodAndServiceActivity : AppCompatActivity() {
    private lateinit var databinding: ActivityFoodandserviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_foodandservice)
        databinding.bottomNavView.setupWithNavController(findNavController(R.id.navHostFragment))
    }
}