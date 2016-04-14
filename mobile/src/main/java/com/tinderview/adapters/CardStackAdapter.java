package com.tinderview.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tinderview.R;
import com.tinderview.datamodule.restaurant_detail;
import com.tinderview.fragments.Connect;
import com.tinderview.networking.AppController;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aradh Pillaion 07/10/15.
 */

/*
* cardStackAdapter which is going to hold list of information and setting it into CardStack
*
* */
public class CardStackAdapter extends ArrayAdapter<restaurant_detail> {

    Bitmap bitmap;
    ProgressDialog pDialog;

    /*public CardStackAdapter(Context context, int resource) {

        super(context, 0);
        new LoadImage().execute("https://www.learn2crack.com/wp-content/uploads/2014/04/node-cover-720x340.png");
    }*/

    public CardStackAdapter(Context context, ArrayList<restaurant_detail> restdet)
{
    super(context, 0, restdet);

}


    @Override
    public View getView(int position, final View contentView, ViewGroup parent) {
        //supply the layout for your card
        restaurant_detail restaurantDetail=getItem(position);
        TextView v = (TextView) (contentView.findViewById(R.id.helloText));
        TextView c = (TextView) (contentView.findViewById(R.id.cuisine));
        TextView avg = (TextView) (contentView.findViewById(R.id.avgcost));
        TextView avgtxt = (TextView) (contentView.findViewById(R.id.avgcosttxt));
        final ImageView iv=(ImageView)(contentView.findViewById(R.id.restimg));
        RatingBar r=(RatingBar)(contentView.findViewById(R.id.rating));
        v.setText(restaurantDetail.getRestname());
        c.setText(restaurantDetail.getRestcuisine());
        avg.setText(restaurantDetail.getAvgtwocost());
        avgtxt.setText("Average cost for two:  ");
        r.setRating(Float.parseFloat(restaurantDetail.getRestrating()));
        r.setClickable(false);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

// If you are using normal ImageView
        imageLoader.get(restaurantDetail.getImgurl(), new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    iv.setImageBitmap(response.getBitmap());
                }
            }
        });

        return contentView;
    }

}
