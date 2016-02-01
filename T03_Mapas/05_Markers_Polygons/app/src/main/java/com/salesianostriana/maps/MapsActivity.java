package com.salesianostriana.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

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

        // Evento click sobre el mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marcadorNY.setPosition(latLng);
                marcadorNY.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });

        // Opciones del marcador
        marcadorNY.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_salesianos_triana));
        marcadorNY.setTitle("New York");
        marcadorNY.setSnippet("Central Park");
        marcadorNY.showInfoWindow();

        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(40.800466, -73.958158),
                        new LatLng(40.796893, -73.949232),
                        new LatLng(40.764266, -73.972921),
                        new LatLng(40.768037, -73.981676),
                        new LatLng(40.800466, -73.958158))
                .strokeColor(Color.RED)
                .strokeWidth(15)
                .fillColor(Color.BLUE);

        // Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newyork,15));
    }
}
