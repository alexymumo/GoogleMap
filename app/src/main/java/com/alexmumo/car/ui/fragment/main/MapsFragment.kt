package com.alexmumo.car.ui.fragment.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alexmumo.car.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

// ktlint-disable no-wildcard-imports

// ktlint-disable no-wildcard-imports

class MapsFragment :
    Fragment(),
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener {
    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        val latitude = -1.1353041
        val longitude = 36.9443908
        val zoomLevel = 15f
        val polyline = googleMap.addPolyline(
            PolylineOptions()
                .color(Color.GRAY)
                .width(5f)
                .clickable(true)
                .add(
                    LatLng(-1.1353041, 36.9443908),
                    LatLng(-1.3028618, 36.7069651),
                    LatLng(-1.5128474, 37.2369496),
                    LatLng(-1.2711339, 37.3028753)
                ),
        )
        polyline.tag = "A"
        polyline.endCap = RoundCap()
        polyline.jointType = JointType.DEFAULT
        val homeLatLng = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(homeLatLng))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))

        enableLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableLocation() {
        if (checkPermission()) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireContext() as Activity, arrayOf(ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enableLocation()
            }
        }
    }

    override fun onPolylineClick(p0: Polyline) {
        TODO("Not yet implemented")
    }

    override fun onPolygonClick(p0: Polygon) {
        TODO("Not yet implemented")
    }
}
