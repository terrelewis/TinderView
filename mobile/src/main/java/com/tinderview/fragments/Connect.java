package com.tinderview.fragments;

import android.os.Bundle;
import   android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tinderview.R;
import com.tinderview.adapters.CardStackAdapter;
import com.tinderview.animation.RippleAnimation;
import com.tinderview.cardstatck.cardstack.CardStack;
import com.tinderview.cardstatck.cardstack.DefaultStackEventListener;
import com.tinderview.datamodule.restaurant_detail;
import com.tinderview.networking.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aradh Pillai on 2/10/15.
 */

/*
* this fragment class is using to show the list of connection into stackCard  based on users location
* and user will have option to discard and like the connection.
*
*
* */
public class Connect extends Fragment {
    RippleAnimation rippleBackground1, rippleBackground2;

    CardStack cardStack;
    //this class is using for swipe the AdapterView
    CardStackAdapter mCardAdapter;
    ArrayList<restaurant_detail> listData = new  ArrayList<>();
    public String Lat, Long;

JSONObject obj;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Lat=getArguments().getString("Lat");
        Long=getArguments().getString("Long");
        String tag_json_arry = "json_array_req";

        String url = " https://developers.zomato.com/api/v2.1/search?lat="+Lat+"&lon="+Long;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("TAG", response.toString() + "");
                        //progressDialogueUtility.progressDialog.cancel();
                        if(response != null && response.length() > 0)
                        {
                            obj = new JSONObject();
                            obj=response;
                            getData();

                        } else {
                            Log.e("TAG", "obj is null");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error: " + error.getMessage() + "");
                //progressDialogueUtility.progressDialog.cancel();
                //pDialog.hide();
            }
        }){ @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("user_key", "2bd94a55333b4797c5d16d0d7b311e31");


            return headers;
        }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("lat", Lat);
                params.put("long", Long);


                return params;
            }





        };


        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect, container, false);

        init(view);// to initialize widgets
        doRippleBackground(); //start ripple background work..
        return view;
    }

    private void init(View view) {
        //root ripple background initialization
        rippleBackground1 = (RippleAnimation) view.findViewById(R.id.content);

        //child ripple background initialization
        // rippleBackground2 = (RippleBackground) view.findViewById(R.id.content2);

        //cardStack initialization
        cardStack = (CardStack) view.findViewById(R.id.frame);

        //at begin setting rippleBackground visibility as VISIBLE and setting CardStack visibility as GONE
        rippleBackground1.setVisibility(View.VISIBLE);
        cardStack.setVisibility(View.GONE);

        //creating adapter

    }

    public void doRippleBackground() {


        //start ripple background animations
        startAnimation();

        //handler created to handle cardStack as well as timer...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                callCardStack();
            }
        }, 8000);

    }

    //start the background ripple animation...
    private void startAnimation() {
        //if it's not running
        if (!rippleBackground1.isRippleAnimationRunning()) {
            rippleBackground1.startRippleAnimation();//start root ripple animation
            // rippleBackground2.startRippleAnimation();//start child ripple animation
        }
    }

    //this method will stop background ripple animation. if it's running.
    private void stopAnimation() {
        if (rippleBackground1.isRippleAnimationRunning()) {
            rippleBackground1.stopRippleAnimation();
            // rippleBackground2.stopRippleAnimation();
        }
    }

    //cardStack view will set it as visible and load the information into stack.
    public void callCardStack() {

        mCardAdapter = new CardStackAdapter(this.getContext(), listData);
        cardStack.setVisibility(View.VISIBLE);
        rippleBackground1.setVisibility(View.GONE);

        stopAnimation();//start the ripple background animation.

        //Setting Resource of CardStack
        cardStack.setContentResource(R.layout.card_stack_item);

        //Adding 30 dummy info for CardStack
        //for (int i = 0; i <= 1; i++)
        //{
            mCardAdapter.addAll(listData);

        //}
        cardStack.setAdapter(mCardAdapter);

        //Setting Listener and passing distance as a parameter ,
        //based on the distance card will discard
        //if dragging card distance would be more than specified distance(100) then card will discard or else card will reverse on same position.
        cardStack.setListener(new DefaultStackEventListener(300));

    }
    public void getData() {
        Log.e("TAG", "get data");
        try {
        JSONArray jsonArray=obj.getJSONArray("restaurants");
        for (int i = 0; i < jsonArray.length(); i++) {
            Log.e("TAG", jsonArray.toString());
            JSONObject obje=jsonArray.getJSONObject(i);
            restaurant_detail rd = new restaurant_detail();

            String rn, ru, rr, rc, rav;




                JSONObject ob= obje.getJSONObject("restaurant");
            JSONObject ob1=ob.getJSONObject("user_rating");
            rn=ob.getString("name");
                rc=ob.getString("cuisines");
                ru= ob.getString("featured_image");
rav=ob.getString("average_cost_for_two");
rr=ob1.getString("aggregate_rating");
                rd.setRestname(rn);
                rd.setImgurl(ru);
            rd.setRestcuisine(rc);
rd.setRestrating(rr);
            rd.setAvgtwocost(rav);

                listData.add(rd);


        }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
