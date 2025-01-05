package com.example.glovoapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.glovoapp.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.util.Locale

class AddDeliveryAddressActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var locationOverlay: MyLocationNewOverlay
    private lateinit var geocoder: Geocoder
    private lateinit var addressInput: EditText
    private lateinit var useCurrentLocationButton: Button
    private lateinit var setAddressButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure OSMDroid
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE))
        setContentView(R.layout.activity_add_delivery_address)

        // Initialize MapView
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true) // Enable pinch zoom

        // Initialize Geocoder
        geocoder = Geocoder(this, Locale.getDefault())

        // Initialize Address Input and Button
        addressInput = findViewById(R.id.edit_address)
        useCurrentLocationButton = findViewById(R.id.location_button)
        setAddressButton = findViewById(R.id.set_address_button)

        // Set default zoom and location
        val mapController = mapView.controller
        mapController.setZoom(15.0)
        mapController.setCenter(GeoPoint(37.7749, -122.4194)) // Example: San Francisco coordinates

        // Set up location overlay
        setupLocationOverlay()

        // Handle address search
        addressInput.setOnEditorActionListener { _, _, _ ->
            val address = addressInput.text.toString()
            if (address.isNotEmpty()) {
                searchAndSetLocation(address)
            } else {
                Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show()
            }
            true
        }

        // Handle "Use Current Location" button click
        useCurrentLocationButton.setOnClickListener {
            setCurrentLocation()
        }
        setAddressButton.setOnClickListener {
            val address = addressInput.text.toString()
            if (address.isNotEmpty()) {
                saveAddressToPreferences(address)
                Toast.makeText(this, "Address saved successfully!", Toast.LENGTH_SHORT).show()
                finish() // Return to the previous activity
            } else {
                Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show()
            }
        }


        /* Handle "Set Address" button click
        setAddressButton.setOnClickListener {
            val address = addressInput.text.toString()
            if (address.isNotEmpty()) {
                returnSelectedAddress(address)
            } else {
                Toast.makeText(this, "Please select a valid address", Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    private fun setupLocationOverlay() {
        val locationProvider = GpsMyLocationProvider(this)
        locationOverlay = MyLocationNewOverlay(locationProvider, mapView)
        locationOverlay.enableMyLocation()
        locationOverlay.enableFollowLocation()
        mapView.overlays.add(locationOverlay)
    }

    private fun searchAndSetLocation(address: String) {
        try {
            val addresses: List<Address>? = geocoder.getFromLocationName(address, 1)
            if (!addresses.isNullOrEmpty()) {
                val location = addresses[0]
                val geoPoint = GeoPoint(location.latitude, location.longitude)
                setMapLocation(geoPoint, address)
            } else {
                Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error searching for address", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }

        locationOverlay.myLocation?.let { geoPoint ->
            val address = getAddressFromGeoPoint(geoPoint)
            setMapLocation(geoPoint, address ?: "Current Location")
        } ?: Toast.makeText(this, "Unable to fetch current location", Toast.LENGTH_SHORT).show()
    }

    private fun setMapLocation(geoPoint: GeoPoint, title: String) {
        mapView.controller.animateTo(geoPoint)
        addMarker(geoPoint, title)
    }

    private fun getAddressFromGeoPoint(geoPoint: GeoPoint): String? {
        return try {
            val addresses: List<Address>? = geocoder.getFromLocation(
                geoPoint.latitude,
                geoPoint.longitude,
                1
            )
            addresses?.get(0)?.getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun addMarker(geoPoint: GeoPoint, title: String) {
        val marker = Marker(mapView)
        marker.position = geoPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = title
        mapView.overlays.clear() // Clear previous overlays
        mapView.overlays.add(marker)
    }


    private fun saveAddressToPreferences(address: String) {
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("DELIVERY_ADDRESS", address)
        editor.apply()
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
