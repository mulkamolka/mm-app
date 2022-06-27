package com.mm.android.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.kakao.util.maps.helper.Utility
import com.mm.android.R
import com.mm.android.address.LocationProvider
import com.mm.android.databinding.ActivityMainBinding
import com.mm.android.databinding.FragmentHomeOptionBinding
import com.mm.android.home.fragment.HomeOptionFragment
import com.mm.android.itemdetail.fragment.ItemDetailTitleFragment
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    // 뷰 바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 런타임 권한 요청 시 필요한 요청 코드
    private val PERMISSIONS_REQUEST_CODE = 100

    // 요청할 권한 목록
    var REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // 위치 서비스 요청 시 필요한 런처
    lateinit var getGPSPermissionLauncher: ActivityResultLauncher<Intent>

    // 위도와 경도 불러올 시 필요
    lateinit var locationManager: LocationManager
    lateinit var locationProvider: LocationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Activity 확장함수

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkAllPermissions()
        updateUI()
//        setRefreshButton()

    }

    // 런타임에서 권한과 위치 서비스 확인
    private fun checkAllPermissions() {
        Log.d("test checkAllPermissions()", "ok")

        // 위치 서비스가 켜져 있지 않을 경우
        if (!isLocationServicesAvailable()) {
            Log.d("test isLocationServicesAvailable()", "no")

            // 위치 서비스 켜기 요청
            showDialogForLocationServiceSetting()

        } else { // 위치 서비스가 켜져 있을 경우
            Log.d("test isLocationServicesAvailable()", "yes")

            // 권한 허용 상태 확인
            isRunTimePermissionsGranted()
        }
    }

    // 위치 서비스 켜기 요청
    private fun showDialogForLocationServiceSetting() {
        Log.d("test showDialogForLocationServiceSetting()", "ok")
        // ActivityResultLauncher를 설정, 이 런처를 이용하여 결괏값을
        // 반환해야하는 인텐트를 실행할 수 잇음
        getGPSPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                // 사용자가 GPS를 활성시켰는지 확인
                if (isLocationServicesAvailable()) {
                    Log.d("test gps", "1")
                    isRunTimePermissionsGranted()
                } else {
                    Log.d("test gps", "2")
                    // 위치 서비스가 허용되지 않았다면 앱 종료
                    Toast.makeText(
                        this@MainActivity,
                        "위치 서비스를 사용할 수 없습니다.",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }

            }

        // 알림 창 생성
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        Log.d("test builder", "yes")
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage(
            "위치 서비스가 꺼져 있습니다. 설정해야 앱을 사용할 수 있습니다."
        )
        builder.setCancelable(true)
        builder.setPositiveButton(
            "설정", DialogInterface.OnClickListener()
            { dialog, id ->
                val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                getGPSPermissionLauncher.launch(callGPSSettingIntent)
                Log.d("test getGPSPermissionLauncher", "getGPSPermissionLauncher")
            })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener
            { dialog, id ->
                dialog.cancel()
                Toast.makeText(
                    this@MainActivity,
                    "기기에서 위치서비스(GPS) 설정 후 사용해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            })
        builder.create().show()
    }

    fun isRunTimePermissionsGranted() {
        Log.d("test isRunTimePermissionsGranted()", "ok")

        // 위치 권한을 가지고 있는지 확인
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this@MainActivity,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this@MainActivity,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )

        Log.d(
            "test requestPermissions", "${
                (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                        hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED
                        )
            }"
        )

        // 권한이 없을 경우 권한 요청
        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
            hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("test requestPermissions", "start")

            // 권한이 없다면 요청

            ActivityCompat.requestPermissions(
                this@MainActivity,
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("test onRequestPermissionsResult()", "ok")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE이고
            // 요청한 퍼미션 개수만큼 수신되었다면
            var checkResult = true

            // 모든 퍼미션을 허용했는지 체크
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }
            if (checkResult) {
                // 위치 값을 가져올 수 있을 경우
                Log.d("test UI", "ok")
                updateUI()
            } else {
                // 퍼미션이 거부되었다면 앱 종료
                Toast.makeText(
                    this@MainActivity,
                    "권한 요청이 거부되었습니다. 앱을 종료하겠습니다.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun setRefreshButton() {
        Log.d("test setRefreshButton()", "ok")
    }

    fun getCurrentAddress(latitude: Double, longtitude: Double): Address? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>

        addresses = try {
            // Geocoder 객체를 통해 위도와 경도로부터 리스트 가져오기
            geocoder.getFromLocation(latitude, longtitude, 7)
        } catch (ioExecption: IOException) {
            Toast.makeText(
                this, "지오코더 서비스 사용불가 합니다.",
                Toast.LENGTH_LONG).show()
            return null
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(
                this, "잘못된 위도, 경도입니다.",
                Toast.LENGTH_LONG).show()
            return null
        }

        if (addresses == null || addresses.size == 0) {
            Toast.makeText(
                this, "주소가 발견되지 않았습니다.",
                Toast.LENGTH_LONG).show()
            return null
        }

        val address: Address = addresses[0]
        return address
    }

    private fun updateUI() {
        Log.d("test updateUI()", "ok")

        locationProvider = LocationProvider(this@MainActivity)

        val latitude: Double = locationProvider.getLocatopmLatitude()
        val longitude: Double = locationProvider.getLocatopmLongitude()

        if (latitude != 0.0 || longitude != 0.0) {
            Log.d("test good lat, lon", "$latitude, $longitude")

            // 현재 위치 가져오고 UI 업데이트
            val address = getCurrentAddress(latitude, longitude)
            address?.let {
                Log.d("test thoroughfare", "${it.thoroughfare}")
                val data = it.thoroughfare
                setDataAtFragment(HomeOptionFragment(), data) }
        } else {
            Log.d("test bad lat, lon", "$latitude, $longitude")

            Toast.makeText(
                this@MainActivity,
                "현재 위도, 경도 정보를 불러올 수 없습니다. 새로고침을 눌러주세요.",
                Toast.LENGTH_LONG
            ).show()
        }



        // 위치 정보 제공자 획득 - 조건 명시법 적용
//        val criteria = Criteria()
//        criteria.accuracy = Criteria.ACCURACY_FINE
//        criteria.isAltitudeRequired = false// 고도 제공
//        criteria.isBearingRequired = false // 방향 제공
//        criteria.isSpeedRequired = false // 속도 제공
//        criteria.isCostAllowed = true // 비용이 드는 것을 허용할 것인지
//        criteria.powerRequirement = Criteria.POWER_LOW // 전원 소모량
//
//        val locationProvider = locationManager.getBestProvider(criteria, true)!!
//
//        // 위치 정보 획득
//        val gpsCheck = ContextCompat.checkSelfPermission(
//            this,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        )
//        if (gpsCheck == PackageManager.PERMISSION_DENIED) {
//            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//        }
//
//        val location1 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//        val location2 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//
////        locationListner  생성
//        val locationListener = object : LocationListener {
//            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//                super.onStatusChanged(provider, status, extras)
//                // provider의 상태가 변경될때마다 호출
//                // deprecated
//            }
//
//            override fun onLocationChanged(location: Location) {
//                // 위치 정보 전달 목적으로 호출(자동으로 호출)
//
//                val longitude = location.longitude
//                val latitude = location.latitude
//
//                Log.d("Location", "Latitude : $latitude, Longitude : $longitude")
//            }
//
//            override fun onProviderEnabled(provider: String) {
//                super.onProviderEnabled(provider)
//                // provider가 사용 가능한 생태가 되는 순간 호출
//            }
//
//            override fun onProviderDisabled(provider: String) {
//                super.onProviderDisabled(provider)
//                // provider가 사용 불가능 상황이 되는 순간 호출
//            }
//        }
//
//        // 현재 위치를 기반으
//        Log.d("test lat, lon", "${location1}")
//        Log.d("test lat, lon", "${location2}")
//        if (location != null) {
//            Log.d("test lat, lon2", "ok")
//            val accuracy = location.accuracy // 정확도
//            val lat = location.latitude // 위도
//            val lon = location.longitude // 경도
//            val alt = location.altitude // 경도
//
//            Log.d(
//                "test all",
//                "accuracy : ${accuracy} latitude : ${lat} longitude : ${lon} altitude : ${alt}"
//            )
//
//            Toast.makeText(
//                this@MainActivity,
//                "accuracy : ${accuracy}, latitude : ${lat}" +
//                        "longitude : ${lon}" + "altitude : ${alt}",
//                Toast.LENGTH_LONG
//            ).show()
//            Log.d("Test location", "complete")
//            getFromLocationName(lat, lon)
//        }


    }

    // reverse geocoder - 위도, 경도 -> 주소
    private fun getFromLocationName(lat: Double, lon: Double, maxResult: Int = 10) {
        Log.d("test getFromLocationName()", "ok")
        val cityList = Geocoder(this.baseContext).getFromLocation(lat, lon, maxResult)
        if (cityList != null) {
            if (cityList.size == 0) {
                Log.d("test cityList", "no")
                Toast.makeText(
                    this@MainActivity,
                    "해당 도시가 없습니다.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                for (i in 0..cityList.size - 1) {
                    Log.d("test cityList", "${cityList.get(i)}")
                }
            }
        }
    }

    // GPS, 네트워크 작동상태 확인
    fun isLocationServicesAvailable(): Boolean {
        Log.d("test isLocationServicesAvailable()", "ok")
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun setDataAtFragment(fragment:Fragment, data:String) {
        // 검색 리스트에서 선택된 아이템 프래그먼트 전달
        val bundle = Bundle()
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("test bundle", "start")

        bundle.putString("data", data)
        fragment.arguments = bundle
        transaction.replace(R.id.optionFragment, fragment)
        transaction.commit()
        Log.d("test bundle", "end")

    }

    override fun onStart() {
        super.onStart()
        Log.d("test onStart()", "start")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("test onRestart()", "start")

    }

    override fun onResume() {
        super.onResume()
        Log.d("test onResume()", "start")
    }

    override fun onPause() {
        super.onPause()
        Log.d("test onPause()", "start")
    }

    override fun onStop() {
        super.onStop()
        Log.d("test onStop()", "start")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
