package com.foodandservice

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.foodandservice.common.Constants.BottomBarVisibleFragments
import com.foodandservice.databinding.ActivityFoodandserviceBinding

class FoodAndServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodandserviceBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_foodandservice)

        navController = findNavController(R.id.navHostFragment)

        binding.apply {
            bottomNavView.setupWithNavController(navController)

            btnQrScan.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@FoodAndServiceActivity,
                        android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openQrScanner()
                } else {
                    ActivityCompat.requestPermissions(
                        this@FoodAndServiceActivity,
                        arrayOf(android.Manifest.permission.CAMERA),
                        100
                    )
                }
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            setBottomBarVisibility(BottomBarVisibleFragments.contains(destination.id))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openQrScanner()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openQrScanner() {
        Intent(this, QRScannerActivity::class.java).also { startActivity(it) }
    }

    private fun setBottomBarVisibility(isVisible: Boolean) {
        binding.bottomAppBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.btnQrScan.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}