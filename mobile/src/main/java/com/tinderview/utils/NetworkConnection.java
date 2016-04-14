package com.tinderview.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {

    public static final String TAG = "Basic Network Demo";

    /**
     * Getting context for the activity
     */
    private Context context;
    /**
     * Get connectivity manager to handle connection queries
     */
    ConnectivityManager connectivityManager;

    public NetworkConnection(Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        /**
         * Get the information of the network using NetworkInfo
         */
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            boolean wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                return true;
            } else if (mobileConnected) {
                return true;
            }
        }
            return false;
    }
}
