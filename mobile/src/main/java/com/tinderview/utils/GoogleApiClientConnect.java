package com.tinderview.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

//import com.fabfresh.gcm.Interface.GCMAsyncProcess;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class GoogleApiClientConnect implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "ApiClient";
    Location location;
    GoogleApiClient mGoogleApiClient;
    Boolean NetworkState;
    View mLayout;

    Context context;

   // public GCMAsyncProcess delegate = null;


    public GoogleApiClientConnect(Context context) {
        this.context = context;
    }



    /**
     * Called to build api client and connect with it
     */
    public void connectClient(){

        Log.e(TAG,"Check api connection");
        CheckGooglePlayServices checkGooglePlayServices = new CheckGooglePlayServices(context);
        if (checkGooglePlayServices.CheckPlayService()) {
            buildGoogleApiClient();
            mGoogleApiClient.connect();
        } else {
            SnackBarAlert snackBarAlert = new SnackBarAlert(context);
            snackBarAlert.CreateSnackBarSimple(mLayout, "This device is not compatible with the app", Snackbar.LENGTH_LONG);
        }
    }



    /**
     * Start android google client api
     */
    protected synchronized void buildGoogleApiClient() {
        Log.e(TAG, "Build client");
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    /**
     * On connection with api client this method in=c invoked
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {

        Log.e(TAG, "Client connected");

        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            double lat = location.getLatitude();
            double lang = location.getLongitude();

            Log.e(TAG, "LatLang : " + lat + " " + lang);

            LatLng _latLng = new LatLng(lat, lang);

//            delegate.getLatLang(_latLng);

        } else {
            statusCheck();
        }
    }


    /**
     * On suspension call to connect with api client, try to reconnect with it
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Try connecting google client again");
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }


    public void statusCheck() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        NetworkConnection networkConnection = new NetworkConnection(context);
        NetworkState = networkConnection.isNetworkConnected();
        if (!NetworkState) {
            AlertDialogNoInternet alertDialogUtil = new AlertDialogNoInternet(context);
            alertDialogUtil.NoInternetDialog(context);
        } else if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialogNoLocation alertDialogNoLocation = new AlertDialogNoLocation(context);
            alertDialogNoLocation.buildAlertMessageNoGps();
        }
    }
}
