package com.mm.android.address

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.util.jar.Manifest

// 위도, 경도, 고도와 같이 위치에 관련된 정보를 가진 클래스
class LocationProvider(val context: Context) {
    private var location: Location? = null
    private var locationManager: LocationManager? = null

    init {
        getLocation()
    }

    private fun getLocation(): Location? {
        try {
            locationManager = context.getSystemService(
                Context.LOCATION_SERVICE
            ) as LocationManager

            var gpsLocation: Location? = null
            var networkLocation: Location? = null

            val isGPSEnabled: Boolean =
                locationManager!!.isProviderEnabled(
                    LocationManager.GPS_PROVIDER
                )

            val isNetworkEnables: Boolean =
                locationManager!!.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER
                )

            // GPS, Network 불가능 시 null 반환
            if (!isGPSEnabled && !isNetworkEnables) {
                return null
            } else {
                val hasFineLocationPermission =
                    ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )

                val hasCoarseLocationPermission =
                    ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )

                if (hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasFineLocationPermission != PackageManager.PERMISSION_GRANTED
                )
                    return null

                if (isNetworkEnables) {
                    networkLocation =
                        locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                }

                if (isGPSEnabled) {
                    gpsLocation =
                        locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                }

                if (gpsLocation != null && networkLocation != null) {
                    if (gpsLocation.accuracy > networkLocation.accuracy) {
                        location = gpsLocation
                        return location
                    } else {
                        location = networkLocation
                        return location
                    }
                } else {
                    if (gpsLocation != null) {
                        location = gpsLocation
                    } else if (networkLocation != null) {
                        location = networkLocation
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return location
    }

    fun getLocatopmLatitude(): Double {
        return location?.latitude ?: 0.0
    }

    fun getLocatopmLongitude(): Double {
        return location?.longitude ?: 0.0
    }
}