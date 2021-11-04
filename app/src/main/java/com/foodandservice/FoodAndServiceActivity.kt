package com.foodandservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FoodAndServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodandservice)
        supportActionBar?.hide()
    }
}