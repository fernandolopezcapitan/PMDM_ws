package com.salesianostriana.maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in NYC
        final LatLng newyork = new LatLng(40.777917,-73.96863);
        final Marker marcadorNY = mMap.addMarker(new MarkerOptions()
                .position(newyork)
                .draggable(true));



        // Opciones del marcador
        marcadorNY.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_salesianos_triana));
        marcadorNY.setTitle("New York");
        marcadorNY.setSnippet("Central Park");
        marcadorNY.showInfoWindow();


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newyork, 15));


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Log.i("MAPA", "MAPA: Me agarraron");
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                LatLng posicionArrastre = marker.getPosition();
                Log.i("MAPA", "MAPA: Me están arrastrando: " + posicionArrastre.latitude + "," + posicionArrastre.longitude);
                marker.hideInfoWindow();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.setSnippet("Me han soltado!");
                LatLng posicionFinal = marker.getPosition();
                marker.showInfoWindow();
            }
        });
    }
}
