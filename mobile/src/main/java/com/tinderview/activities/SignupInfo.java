package com.tinderview.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tinderview.R;
import com.tinderview.networking.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by terrelewis on 7/4/16.
 */
public class SignupInfo extends Activity {


    EditText username, gender;
    Button signup;
    JsonObjectRequest userObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupinfo);
        username=(EditText)findViewById(R.id.Username);
        gender=(EditText)findViewById(R.id.gender);
        signup=(Button)findViewById(R.id.signupconfirm);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernam=username.getText().toString();
                String sex=gender.getText().toString();
                JSONObject userobject=new JSONObject();
                JSONArray restaurantlist=new JSONArray();
                        restaurantlist.put(4);

                    try {
                        userobject.put("name", usernam);
                        userobject.put("sex", sex);
                        userobject.put("restaurant", restaurantlist);
                        Log.e("TAG", userobject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                userObject = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://terrelewis.pythonanywhere.com/plateup/users/",
                        userobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                               // Log.e(TAG +"response", String.valueOf(response) + "");
                                //progressDialogueUtility.progressDialog.cancel();
                                if (response != null && response.length() > 0) {
                                    try {
                                        String status = response.getString("id");
                                        if (Integer.parseInt(status)>0) {
                                            Log.d("TAG", "User created");
                                            Intent i=new Intent(SignupInfo.this, LogIn.class);
                                            startActivity(i);
                                            finish();


                                        } else {
                                            Intent i=new Intent(SignupInfo.this, LogIn.class);
                                            startActivity(i);
                                            finish();


                                            Log.e("TAG","error");


                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.v("Error response", String.valueOf(error));
                                //progressDialogueUtility.progressDialog.cancel();
                                if ( error instanceof TimeoutError) { Log.e("TAG","error"); }
                                else if (error instanceof AuthFailureError) { Log.e("TAG","error"); }
                                else if (error instanceof ServerError) {Log.e("TAG","error");  }
                                else if (error instanceof ParseError) { Log.e("TAG","error"); }
                                else if (error instanceof NoConnectionError) {  Log.e("TAG","error");}
                                else if (error instanceof NetworkError) { Log.e("TAG","error"); }
                                else { Log.e("TAG","error"); }

                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();

                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers != null ? headers : super.getHeaders();
                    }
                };
                String TAG="User signup";
                userObject.setTag(TAG);
                userObject.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                AppController.getInstance().getRequestQueue().cancelAll(TAG);
                AppController.getInstance().addToRequestQueue(userObject);

            }
        });
    }
}
