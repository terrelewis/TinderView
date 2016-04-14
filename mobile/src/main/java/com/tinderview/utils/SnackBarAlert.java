package com.tinderview.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import com.tinderview.R;


public class SnackBarAlert {

    Context context;
    Snackbar snackbar;

    public SnackBarAlert(Context context) {
        this.context = context;
    }

    public void CreateSnackBarSimple(View view, String message, int duration){
        snackbar = Snackbar.make(view, message, duration);
        View SnackBarView  = snackbar.getView();
        SnackBarView.setBackgroundColor(context.getResources().getColor(R.color.splashProgress));
        SnackBarView.setKeepScreenOn(false);
        TextView tv = (TextView) SnackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextSize(20);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        snackbar.show();
    }
}
