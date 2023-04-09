package com.foodandservice.util

import android.content.Context
import android.content.Intent
import android.net.Uri

class IntentUtils {
    companion object {
        fun openMapsWithLocation(
            latitude: Double, longitude: Double, zoomLevel: Int = 20, context: Context
        ) {
            val uri = Uri.parse("geo:$latitude,$longitude,?z=${zoomLevel}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            context.startActivity(intent)
        }

        fun openPhoneWithNumber(phone: String, context: Context) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            context.startActivity(intent)
        }
    }
}