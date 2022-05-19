package com.example.carrotmarket.join

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.databinding.ActivityJoinBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.util.*

class JoinActivity : AppCompatActivity() {

    lateinit var binding : ActivityJoinBinding
    lateinit var joinViewModel: JoinViewModel
    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)
        setContentView(binding.root)

        setObserver()
//        location()
        joinBtn()

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

        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>? = geocoder.getFromLocation(lat, lon, 1)
        try {
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]

                if(returnedAddress.adminArea == null) city = returnedAddress.locality
                else city = returnedAddress.adminArea

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

    private fun setObserver() {
        joinViewModel.result.observe(this, Observer {
            if (it.status == "success") {
                getSharedPreferences("login", MODE_PRIVATE).edit().putString("id",it.cookie).apply()
                Toast.makeText(this, "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show()
                Log.d("loginCheck","${it.cookie}")
                finish()
            }else{
                Toast.makeText(this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun joinBtn(){
        if(binding.userId.text != null){
            binding.joinBtn.setOnClickListener {
                joinViewModel.joinInfoToServer(binding.userId.text.toString(),binding.userPw.text.toString(),binding.userName.text.toString(),)
            }
        }
    }

}