package com.tinderview.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class CheckGooglePlayServices {

    Context context;
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    View mLayout;

    public CheckGooglePlayServices(Context context) {
        this.context = context;
    }

    /**
     * Check google play service exists in device or not
     */
    public boolean CheckPlayService(){
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.isUserRecoverableError(resultCode);
            GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) context, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            return false;
        }
        return true;
    }
}
