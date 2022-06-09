package com.alexmumo.car.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexmumo.car.R
import com.alexmumo.car.databinding.FragmentMapsBinding
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.maps.* // ktlint-disable no-wildcard-imports
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapsBinding
    private lateinit var geoclient: GeofencingClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onMapReady(googleMap: GoogleMap) {
        // Coordinates
        val dekut = LatLng(-0.3977286, 36.9575167)
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        // Add marker to the map
        googleMap.addMarker(
            MarkerOptions()
                .title("Dekut")
                .position(dekut)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(dekut))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
        googleMap.isTrafficEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this) // getMapAsync used to register callback
    }
}

/*
* OnMapReadyCallBack interface
* -
* */
