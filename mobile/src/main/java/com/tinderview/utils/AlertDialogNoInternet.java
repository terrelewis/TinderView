package com.tinderview.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.tinderview.R;


public class AlertDialogNoInternet {

    Context context;

    public AlertDialogNoInternet(Context context) {
        this.context = context;
    }

    public void NoInternetDialog(final Context context){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(context.getString(R.string.no_internet_head));
        alertDialog.setMessage(context.getString(R.string.no_internet_dialogue));
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                dialog.cancel();
                System.exit(0);
            }
        });
        alertDialog.show();
    }
}
