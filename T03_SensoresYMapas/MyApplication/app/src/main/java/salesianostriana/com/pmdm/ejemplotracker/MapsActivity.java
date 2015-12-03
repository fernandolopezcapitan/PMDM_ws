package salesianostriana.com.pmdm.ejemplotracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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

        // Add a marker in Sydney and move the camera
        final LatLng colegio = new LatLng(37.380336, -6.007744);

        final Marker marcador_colegio = mMap.addMarker(new MarkerOptions()
                .position(colegio)
                .draggable(true));

        //Opciones del marcador
        //marcador_colegio.setIcon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_dialog_map));
        marcador_colegio.setTitle("Colegio Salesiano");
        marcador_colegio.setSnippet("Triana");
        marcador_colegio.showInfoWindow();

        //Marker marcador = mMap.addMarker(new MarkerOptions().position(new LatLng(36,7)).title("YO"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(colegio, 15));

        // Evento click sobre el mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Marker marcador1 = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .draggable(true));

                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Log.i("MAPA", "MAPA: Me agarraron");
                //LatLng pos_ini = marker.getPosition();
                //marker.setPosition(pos_ini);
            }
            @Override
            public void onMarkerDrag(Marker marker) {
                LatLng posicionArrastre = marker.getPosition();

                /*PolygonOptions rectOptions = new PolygonOptions()
                        .add(marcad)
                        .strokeColor(Color.RED)
                        .strokeWidth(15)
                        .fillColor(Color.BLUE);*/

                Polygon polygon = mMap.addPolygon(rectOptions);
                polygon.setVisible(true);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(posicionArrastre));
                Log.i("MAPA", "MAPA: Me están arrastrando: " + posicionArrastre.latitude + "," + posicionArrastre.longitude);
                marker.hideInfoWindow();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.setSnippet("Me han soltado!");
                LatLng posicionFinal = marker.getPosition();
                marker.setPosition(posicionFinal);
                marker.showInfoWindow();
            }
        });
    }
}
