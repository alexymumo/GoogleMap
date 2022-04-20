package com.alexmumo.car

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexmumo.car.databinding.ActivityMapsBinding
import com.google.android.gms.maps.* // ktlint-disable no-wildcard-imports
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    val sydney = LatLng(-1.6118365, 37.4812825)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Adds a marker to the coordinates
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        mMap.setMinZoomPreference(6.0f)
        mMap.setMaxZoomPreference(14f)

        // centers the map at LatLng coordinates
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
