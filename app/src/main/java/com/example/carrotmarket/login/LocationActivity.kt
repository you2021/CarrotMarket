package com.example.carrotmarket.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.MainActivity
import com.example.carrotmarket.databinding.ActivityLocationBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.util.*

class LocationActivity : AppCompatActivity() {
    lateinit var binding : ActivityLocationBinding
    lateinit var cityViewModel: CityViewModel

    var city = ""
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        cityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        location()
        btn()
    }

    fun setObserver(){
        cityViewModel.result.observe(this, {  // 동네설정
            if (it.status == "success") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    fun location(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("로그", "startLocationUpdates() 두 위치 권한중 하나라도 없는 경우 ")
            return
        }

        Log.d("로그", "startLocationUpdates() 위치 권한이 하나라도 존재하는 경우")

        val mLocationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        val locCurrent = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val lat = locCurrent?.latitude
        val lon = locCurrent?.longitude
        Log.d("로그", "LATITUDE : $lat, LONGITUDE : $lon")

        val mapView = MapView(this)

        val mapPoint = MapPoint.mapPointWithGeoCoord(lat!!, lon!!)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 3, true);

        binding.mapView.addView(mapView)

        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>? = geocoder.getFromLocation(lat, lon, 1)
        try {
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]

                if(returnedAddress.adminArea == null) city = returnedAddress.locality
                else city = returnedAddress.adminArea

                binding.cityTxt.text = city

                Log.d("로그", city)
            } else {
                Log.d("로그", "No Address returned!")
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.d("로그", "Canont get Address!")
        }

        val marker = MapPOIItem()
        marker.itemName = city
        marker.tag = 0
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

        marker.selectedMarkerType =
            MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker)
    }

    fun btn(){
        binding.changeBtn.setOnClickListener {
            cityViewModel.cityToServer(city)

        }
    }

}