package com.dam.salesianostriana.pmdm.contenedorescercanos;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Date;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    Marker marker, markerC1, markerC2, markerC3, markerC4, markerC5, marcadorUsuario;
    private GoogleApiClient mGoogleApiClient = null;
    private Location mCurrentLocation;
    private String mLastUpdateTime;
    private boolean mRequestingLocationUpdates = true;
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // 1. Instancio un objeto de tipo GoogleApiClient
        buildGoogleApiClient();

        // 2. Activar la detección de localización
        createLocationRequest();
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                        // Indico que la API que voy a utilizar
                        // dentro de Google Play Services, es la API
                        // del Servicio de Localización
                .addApi(LocationServices.API)
                .build();

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        // Intervalo de uso normal de la la aplicación
        mLocationRequest.setInterval(10000);
        // Interval de una app que requiera una localización exhaustiva
        mLocationRequest.setFastestInterval(5000);
        // GPS > mejor método de localización / consume más batería
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
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


        LatLng contenedor1 = new LatLng(37.379576, -6.006909);
        LatLng contenedor2 = new LatLng(37.380053, -6.007386);
        LatLng contenedor3 = new LatLng(37.380676, -6.008030);
        LatLng contenedor4 = new LatLng(37.265780, -6.063827);
        LatLng contenedor5 = new LatLng(37.265723, -6.064186);


        markerC1 = mMap.addMarker(new MarkerOptions().position(contenedor1).title("Contenedor 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_contenedor)));
        markerC2 = mMap.addMarker(new MarkerOptions().position(contenedor2).title("Contenedor 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_contenedor)));
        markerC3 = mMap.addMarker(new MarkerOptions().position(contenedor3).title("Contenedor 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_contenedor)));
        markerC4 = mMap.addMarker(new MarkerOptions().position(contenedor4).title("Contenedor 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_contenedor)));
        markerC5 = mMap.addMarker(new MarkerOptions().position(contenedor5).title("Contenedor 5").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_contenedor)));



        mMap.animateCamera(CameraUpdateFactory.newLatLng(contenedor1));
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("POSICION", "POSICION: onLocationChanged");
        // guardo en la variable mCurrentLocation la
        // localización del usuario
        mCurrentLocation = location;
        // guardo la última vez que se actualizó la posición
        // del usuario en un objeto de tipo String
        // (en nuestro ejemplo no lo estamos utilizando)
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();

    }


    // Este método se encarga de actualizar la Interfaz de Usuario
    // Cada vez que se actualiza la posición del dispositivo.
    private void updateUI() {
        // Transformo el objeto "mCurrentLocation" de tipo Location
        // a un objeto de tipo LatLng
        // lo hago mediante los métodos: mCurrentLocation.getLatitude()
        // y mCurrentLocation.getLongitude()

        LatLng posicionUsuario = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());

        marcadorUsuario = mMap.addMarker(new MarkerOptions().position(posicionUsuario).draggable(true));

        CircleOptions radioBusqueda = new CircleOptions().center(posicionUsuario).radius(100.00).fillColor(getResources().getColor(R.color.transparecia));

        // No coje el método si es un circulo en lugar de un polígono....
        //boolean dentroCirculo = PolyUtil.containsLocation(posicionUsuario,radioBusqueda,true);
       /* if
        SphericalUtil.computeDistanceBetween(L)*/



        // Get back the mutable Circle
        Circle circle = mMap.addCircle(radioBusqueda);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicionUsuario,13));

    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i("POSICION","POSICION: onConnected");
        // La siguiente condición indica que sólo se inicie actualización
        // de la localización del usuario, si tengo activado la "escucha"
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            stopLocationUpdates();
        }
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        mRequestingLocationUpdates = false;
        Log.i("POSICION","POSICION: stopLocationUpdates");
    }

    protected void startLocationUpdates() {

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        mRequestingLocationUpdates = true;
        Log.i("POSICION", "POSICION: startLocationUpdates");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

}