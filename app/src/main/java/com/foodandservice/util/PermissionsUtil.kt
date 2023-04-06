package com.foodandservice.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsUtil {
    companion object {
        var alreadyCheckedForLocationPermissionStatus = false

        fun hasLocationPermission(context: Context) = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
        ).all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

        fun shouldShowRationale(activity: Activity, vararg permissions: String) =
            permissions.all { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, permission
                )
            }

        fun locationPermissionStatusChecked() {
            alreadyCheckedForLocationPermissionStatus = true
        }

        fun isLocationPermissionStatusChecked() = alreadyCheckedForLocationPermissionStatus
    }
}