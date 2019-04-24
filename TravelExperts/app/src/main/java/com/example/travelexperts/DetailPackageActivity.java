package com.example.travelexperts;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DetailPackageActivity extends AppCompatActivity {
     Button btnOrder, btnLogout, btnView;
     TextView tvpkgName, tvpkgStartDate,
             tvpkgDesc, tvpkgEndDate, tvpkgBasePrice;

     SessionManager session;
    // To do these methods need to be created to show the package details based on the JSON info
    // Each img button will need a method or sql statement to call that specific package
    // here we need a pkgId to call that specific package

    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_package);

        initializeBtns();
        initializeFields();

        session = new SessionManager(DetailPackageActivity.this);

        rQueue = Volley.newRequestQueue(DetailPackageActivity.this);

        getPackageDetailsJSON();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

    }

    private void getPackageDetailsJSON()
    {
        String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/packages/getpackageid/1";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            JSONArray jsonArray = jsonObject.getJSONArray("packages");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject packages = jsonArray.getJSONObject(i);

                                String pkgName = packages.getString("pkgName");
                                String pkgStartDate = packages.getString("pkgStartDate");
                                String pkgEndDate = packages.getString("pkgEndDate");
                                String pkgDesc = packages.getString("pkgDesc");
                                String pkgBasePrice = packages.getString("pkgBasePrice");

                                tvpkgName.setText(pkgName);
                                tvpkgStartDate.setText(pkgStartDate);
                                tvpkgEndDate.setText(pkgEndDate);
                                tvpkgDesc.setText(pkgDesc);
                                tvpkgBasePrice.setText(pkgBasePrice);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("RVA", "error:" + error);

                int errorCode = 0;

                if (error instanceof TimeoutError) {
                    errorCode = -7;
                } else if (error instanceof NoConnectionError) {
                    errorCode = -2;
                } else if (error instanceof AuthFailureError) {
                    errorCode = -6;
                } else if (error instanceof ServerError) {
                    errorCode = 0;
                } else if (error instanceof NetworkError) {
                    errorCode = -1;
                } else if (error instanceof ParseError) {
                    errorCode = -8;
                }
                Toast.makeText(DetailPackageActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();
            }
        });

        rQueue.add(request);
    }

    // this is the post that will insert a new booking into the REST service
    private void insertBookingDetails
    {
        // this is the string URL for the bookings post
        String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/Booking/booking";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                {
                    Toast.makeText(DetailPackageActivity.this, "Package order successful!", Toast.LENGTH_SHORT).show();
                    custOrderAnotherPackage();
                }
                else
                {
                    Toast.makeText(DetailPackageActivity.this, "UOOOOOOOOH!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("RVA", "error:" + error);

                int errorCode = 0;

                if (error instanceof TimeoutError) {
                    errorCode = -7;
                } else if (error instanceof NoConnectionError) {
                    errorCode = -2;
                } else if (error instanceof AuthFailureError) {
                    errorCode = -6;
                } else if (error instanceof ServerError) {
                    errorCode = 0;
                } else if (error instanceof NetworkError) {
                    errorCode = -1;
                } else if (error instanceof ParseError) {
                    errorCode = -8;
                }
                Toast.makeText(DetailPackageActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();
            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> parameters = new HashMap<String, String>();
////                parameters.put("CustomerId"); these variables need to be the customerId brought in from the session variable of the main activity login
////                parameters.put("PackageId"); this needs to be the package id brought in from the package activity
//                return parameters;
//
//            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(DetailPackageActivity.this);
        requestQueue.add(request);

    }

    private void initializeBtns ()
    {
        btnOrder = findViewById(R.id.btnOrder);
        btnLogout = findViewById(R.id.btnLogout);
        btnView = findViewById(R.id.btnView);
    }

    private void initializeFields()
    {
        tvpkgName = findViewById(R.id.tvpkgName);
        tvpkgStartDate = findViewById(R.id.tvpkgStartDate);
        tvpkgEndDate = findViewById(R.id.tvpkgEndDate);
        tvpkgDesc = findViewById(R.id.tvpkgDesc);
        tvpkgBasePrice = findViewById(R.id.tvpkgBasePrice);
    }

    private void custOrderAnotherPackage()
    {
        DialogInterface.OnClickListener dialogClickListerner = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent packageMain = new Intent(getApplicationContext(), PackageActivity.class);
                        startActivity(packageMain);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        session.logoutUser();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPackageActivity.this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListerner)
                .setNegativeButton("No", dialogClickListerner).show();

    }

}
