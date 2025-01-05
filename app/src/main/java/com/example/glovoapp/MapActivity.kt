package com.example.osmdroidexample


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.R
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure OSMDroid
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE))

        setContentView(R.layout.activity_map)

        // Initialize MapView
        mapView = findViewById(R.id.mapView)
        mapView.setMultiTouchControls(true) // Enable pinch zoom

        // Set default zoom and location
        val mapController = mapView.controller
        mapController.setZoom(15.0)
        mapController.setCenter(GeoPoint(37.7749, -122.4194)) // Example: San Francisco coordinates

        // Add a marker
        val marker = Marker(mapView)
        marker.position = GeoPoint(37.7749, -122.4194)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "San Francisco"
        mapView.overlays.add(marker)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume() // Needed for OSMDroid
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause() // Needed for OSMDroid
    }
}
