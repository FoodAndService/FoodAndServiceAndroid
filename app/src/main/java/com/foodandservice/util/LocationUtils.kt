package com.foodandservice.util

import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.foodandservice.domain.model.location.Coordinate

class LocationUtils {
    companion object {
        private var userLatitude = 0.0
        private var userLongitude = 0.0
        var defaultCoordinates = false

        fun saveUserCoordinates(coordinate: Coordinate) {
            userLatitude = coordinate.latitude
            userLongitude = coordinate.longitude
        }

        fun getUserCoordinates() = Coordinate(latitude = userLatitude, longitude = userLongitude)

        fun deleteUserCoordinates() {
            userLatitude = 0.0
            userLongitude = 0.0
        }

        fun hasUserCoordinates() = userLatitude != 0.0 && userLongitude != 0.0

        fun isGPSEnabled(context: Context): Boolean {
            return (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
        }

        fun getDistanceInKmBetweenTwoCoordinates(
            firstCoordinate: Coordinate, secondCoordinate: Coordinate
        ): Float {
            val firstLocation = Location("First location")
            val secondLocation = Location("Second location")

            firstLocation.apply {
                latitude = firstCoordinate.latitude
                longitude = firstCoordinate.longitude
            }

            secondLocation.apply {
                latitude = secondCoordinate.latitude
                longitude = secondCoordinate.longitude
            }

            return firstLocation.distanceTo(secondLocation) / 1000
        }
    }
}