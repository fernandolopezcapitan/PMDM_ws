package com.salesianostriana.dam.pmdm.prisionbreakflc;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.text.DateFormat;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient = null;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private String mLastUpdateTime;
    private Marker marker;
    private boolean mRequestingLocationUpdates = true;

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

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_locate:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

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

        // añadir un marcador en el colegio y mover la cámara
        LatLng clase = new LatLng(37.380285, -6.007007);

        final Marker marcador_clase = mMap.addMarker(new MarkerOptions()
                .position(clase)
                .draggable(true));

        //Opciones del marcador
        //marcador_clase.setIcon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_dialog_map));
        marcador_clase.setTitle("clase 2º dam");
        marcador_clase.setSnippet("Colegio Salesiano, Triana");
        marcador_clase.showInfoWindow();

        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(37.380258, -6.007671),
                        new LatLng(37.380941, -6.007577),
                        new LatLng(37.380958, -6.007335),
                        new LatLng(37.381385, -6.006627),
                        new LatLng(37.380622, -6.005769),
                        new LatLng(37.379628, -6.006906))
                .strokeColor(Color.RED)
                .strokeWidth(15)
                .fillColor(Color.BLUE);

        // Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);

        //mMap.addMarker(new MarkerOptions().position(colegio).title("Marcador en Colegio Salesiano"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clase, 15));
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
        LatLng posicion = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        marker.setPosition(posicion);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicion, 13));

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
        Log.i("POSICION", "POSICION: stopLocationUpdates");
    }

    protected void startLocationUpdates() {

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        mRequestingLocationUpdates = true;
        Log.i("POSICION","POSICION: startLocationUpdates");
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

/*
    // The problem with the default functions in Google Map API is they only use two points for the polygon.
// With this new function we are going to use all the points.
    GPolygon.prototype.containsLatLng = function(latLng) {

        // Firstly we try with the bounds (remember, it's a rectangle). We can avoid to do the calculation.
        // If the point it's outside of the rectangle then it's outside of the polygon.
        var bounds = this.getBounds();
        if(!bounds.containsLatLng(latLng)) {
            return false;
        }

        // Now we know the point is inside of the rectangle but we have to check if it's inside of the polygon

        var numPoints = this.getVertexCount();
        var inPoly = false;
        var i;
        var j = numPoints-1;

        for(var i=0; i < numPoints; i++) {
            var vertex1 = this.getVertex(i);
            var vertex2 = this.getVertex(j);

            if (vertex1.lng() < latLng.lng() && vertex2.lng() >= latLng.lng() || vertex2.lng() < latLng.lng() && vertex1.lng() >= latLng.lng())  {
                if (vertex1.lat() + (latLng.lng() - vertex1.lng()) / (vertex2.lng() - vertex1.lng()) * (vertex2.lat() - vertex1.lat()) < latLng.lat()) {
                    inPoly = !inPoly;
                }
            }

            j = i;
        }

        return inPoly;
    };*/
    public boolean dentroRectangulo (LatLng latLng) {

        boolean bounds = this.getBounds();
        if(!bounds.containsLatLng(latLng)) {
            return false;
        }
        return false;
    }
}
