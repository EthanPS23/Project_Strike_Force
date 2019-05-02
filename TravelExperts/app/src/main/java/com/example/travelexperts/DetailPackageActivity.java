// Author: Chris Potvin
// Date: Wednesday, May 1, 2019
// About: This class connects is the main activity and also works as the login page. User credentials are checked against the REST service,
// with a POST via their email and password stored in the mySQL db. Their is also a session variable to handle logins to store the keys from one
// activity to another. The second method is another POST to the REST service were we are retrieving the Customer ID from the DB depending
// on the customers email and password. This ID is passed to the package detail activity. Where the customer can succesfully book a package based
// on which one he selects and orders.

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class DetailPackageActivity extends AppCompatActivity {
     Button btnOrder, btnLogout, btnView;
     TextView tvpkgName, tvpkgStartDate,
             tvpkgDesc, tvpkgEndDate, tvpkgBasePrice, tvTitle;

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

        // this will retrieve the string from Main Activity customer ID

        Intent intent = getIntent();
        String custId = intent.getStringExtra("custID");
        intent.putExtra("custID", custId);


        Package pkg = (Package) intent.getSerializableExtra("pkg");
        intent.putExtra("packageId", pkg);
        System.out.println(pkg);



        tvpkgName.setText(pkg.getPkgName());
        tvpkgBasePrice.setText(String.valueOf(pkg.getPkgBasePrice()));
        tvpkgDesc.setText(pkg.getPkgDesc());
        tvpkgStartDate.setText(pkg.getPkgStartDate());
        tvpkgEndDate.setText(pkg.getPkgEndDate());


        session = new SessionManager(DetailPackageActivity.this);


        // on logout the customer is now finished his session
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Toast.makeText(DetailPackageActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();

            }
        });

        // this on click listener calls on the POST method to insert a new booking based on the package the customer selects

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBookingDetails();
            }
        });

    }

    // this is the post that will insert a new booking into the REST service
    //  Author: Chris Potvin Co-author: Ethan Shipley
    private void insertBookingDetails()
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                Intent intent = getIntent();

                // grab the customer ID from the DetailCustomerActivity
                String custId = intent.getStringExtra("custID");
//                System.out.println(custId); // error checking to ensure we grab the cust id
                intent.putExtra("custID", custId);

                // grab the package ID from the Package

                Package pkg = (Package) intent.getSerializableExtra("pkg");
                intent.putExtra("packageId", pkg);
//                System.out.println(pkg); // error checking to ensure we grab the package id


                parameters.put("CustomerId", custId );
                parameters.put("PackageId", Integer.toString(pkg.getPackageId()));
                return parameters;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DetailPackageActivity.this);
        requestQueue.add(request);

    }

    //this method initializes the buttons on the activity

    private void initializeBtns ()
    {
        btnOrder = findViewById(R.id.btnOrder);
        btnLogout = findViewById(R.id.btnLogout);
    }

    // this method initializes the fields

    private void initializeFields()
    {
        tvpkgName = findViewById(R.id.tvpkgName);
        tvpkgStartDate = findViewById(R.id.tvpkgStartDate);
        tvpkgEndDate = findViewById(R.id.tvpkgEndDate);
        tvpkgDesc = findViewById(R.id.tvpkgDesc);
        tvpkgBasePrice = findViewById(R.id.tvpkgBasePrice);
        tvTitle = findViewById(R.id.tvTitle);
    }

    // Author: Chris Potvin
    // Prototype, customer is able to order only one package but if they tap on no, then they are logged out via their session.
    // I think the app breaks because the session is not continued in the BUTTON_POSITIVE.

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
                        Toast.makeText(DetailPackageActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPackageActivity.this);
        builder.setMessage("Would you like to order another package?").setPositiveButton("Yes", dialogClickListerner)
                .setNegativeButton("No", dialogClickListerner).show();
    }


}
