package com.example.carrotmarket.join

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityLocationBinding
import com.example.carrotmarket.databinding.ActivityLocationMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    val TAG: String = "로그"
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityLocationMapsBinding
    var latLng: LatLng? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "startLocationUpdates() 두 위치 권한중 하나라도 없는 경우 ")
            return
        }

        Log.d(TAG, "startLocationUpdates() 위치 권한이 하나라도 존재하는 경우")

        val mLocationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        val locCurrent = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val lat = locCurrent!!.latitude
        val lon = locCurrent.longitude
        latLng = LatLng(lat, lon)
        Log.d(TAG, "LATITUDE : $lat, LONGITUDE : $lon")

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions().position(latLng!!).title("현재")
        )

        val setting = googleMap.uiSettings
        setting.isZoomControlsEnabled = true
        setting.isMyLocationButtonEnabled = true

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "startLocationUpdates() 두 위치 권한중 하나라도 없는 경우 ")
            return
        }
        googleMap.isMyLocationEnabled = true

    }

}

