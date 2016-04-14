package com.tinderview.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogueUtility {

    /**
     * Progress bar components
     */
    public ProgressDialog progressDialog;

    /**
     * Getting context for the activity
     */
    private Context context;

    public ProgressDialogueUtility(Context context) {
        this.context = context;
    }

    /**
     * Progress bar
     * @Cancelable
     */
    public void StartProgressCancelable() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressNumberFormat(null);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    /**
     * Progress bar
     * @NonCancelable
     */
    public void StartProgressNonCancelable() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressNumberFormat(null);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}
