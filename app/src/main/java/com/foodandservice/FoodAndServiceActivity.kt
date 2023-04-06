package com.foodandservice

import android.Manifest.permission.CAMERA
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.foodandservice.databinding.ActivityFoodandserviceBinding
import com.foodandservice.util.extensions.CoreExtensions.openAppSystemSettings
import com.foodandservice.util.extensions.CoreExtensions.showDialog

class FoodAndServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodandserviceBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openQrScanner()
        } else {
            if (!shouldShowRequestPermissionRationale(CAMERA)) {
                showDialog(title = getString(R.string.dialog_permission_missing),
                    description = getString(R.string.dialog_permission_missing_camera),
                    btnPositiveLabel = getString(
                        R.string.btn_open_settings
                    ),
                    onBtnPositiveClick = {
                        openAppSystemSettings()
                    })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_foodandservice)

        navController = findNavController(R.id.navHostFragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.cartFragment,
                R.id.ordersFragment,
                R.id.accountFragment
            )
        )

        binding.apply {
            bottomNavView.setupWithNavController(navController)

            btnQrScan.setOnClickListener {
                if (hasCameraPermission()) {
                    openQrScanner()
                } else {
                    requestPermissionLauncher.launch(CAMERA)
                }
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            setBottomBarVisibility(appBarConfiguration.topLevelDestinations.contains(destination.id))
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun openQrScanner() {
        Intent(this, QRScannerActivity::class.java).also { startActivity(it) }
    }

    private fun setBottomBarVisibility(isVisible: Boolean) {
        binding.bottomAppBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.btnQrScan.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}