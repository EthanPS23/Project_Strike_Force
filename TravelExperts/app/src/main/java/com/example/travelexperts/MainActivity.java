// Author: Chris Potvin
// Date: Wednesday, May 1, 2019
// About: This class connects is the main activity and also works as the login page. User credentials are checked against the REST service,
// with a POST via their email and password stored in the mySQL db. Their is also a session variable to handle logins to store the keys from one
// activity to another. The second method is another POST to the REST service were we are retrieving the Customer ID from the DB depending
// on the customers email and password. This ID is passed to the package detail activity. Where the customer can succesfully book a package based
// on which one he selects and orders.


package com.example.travelexperts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    // Colors and design at the preliminary stage. Button colors and link colors can be changed as desired by the group

    String custId; // custId variable to be passed to the next activity

    private SharedPreferences pref;

    Button btnSignUp, btnLogin;
    EditText etEmail, etPassword;

    SessionManager session; // call the session manager class to create a session variable

    String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/Login/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to do login page, encryption or google sign in **

        //initialize the buttons method
        initializeBtns();

        //initialize the fields
        initializeFields();

        // go to customer registration page if sign in btn is clicked
        getCustDetails();

        //Session manager
        session = new SessionManager(MainActivity.this);

        // go to packages page if login authorization is correct on login btn
        // if not error message with a toast, to do -> create the toast
        viewPackages();
    }

    private void initializeBtns ()
    {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void initializeFields()
    {
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);

    }

    private void getCustDetails(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent custRegistration = new Intent(getApplicationContext(), DetailCustomerActivity.class);
                startActivity(custRegistration);
            }
        });
    }

    private void viewPackages(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(etEmail.getText());
                getcustomerId();

                // String to connect to the REST Services
                final StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // if login successful grant access to the packages page
                         {
                        if (!response.equals("false")) {


                            session.createLoginSession("CustEmail", "CustPassword");

                            session.isLoggedIn();


                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show(); // this creates the login session based on the post to the webservice

                            finish();


                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect Login Details", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();

                    }
                })
                    {
                        protected Map<String, String> getParams() throws AuthFailureError{
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("CustEmail", etEmail.getText().toString());
                            parameters.put("CustPassword", etPassword.getText().toString());

                        return parameters;
                    }
                    };

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(request);

                }


        });

    }


    //Author: Chris Potvin
    //this method returns the customerID based on the REST service

    private void getcustomerId(){
//        final String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/Login/getcustomerid";
        final StringRequest request = new StringRequest(Request.Method.POST, "http://10.163.37.7:8080/TravelExpertsWebApp/rest/Login/getcustomerid", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.equals("false")) {

//                    Toast.makeText(MainActivity.this, "Customer Id retrieved", Toast.LENGTH_SHORT).show(); // this creates the login session based on the post to the webservice
                    System.out.println(response);

                    Intent packageMain = new Intent(getApplicationContext(), PackageActivity.class);
                    packageMain.putExtra("custID", response);
                    startActivity(packageMain);


//                            Intent packageMain = new Intent(getApplicationContext(), PackageActivity.class);
//                            packageMain.putExtra("custId", response);

                             // this should print out the customerId//                            startActivity(packageMain);

                            finish();

                } else {
//                    Toast.makeText(MainActivity.this, "ERRRRROOOORRR", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();
            }

        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("CustEmail", etEmail.getText().toString());
                parameters.put("CustPassword", etPassword.getText().toString());

                return parameters;
            }
        };



        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }


    public void webappLinkOpen(View view){
        /*Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.163.37.7:8080/TravelExpertsWebApp"));
        startActivity(browserIntent);*/ // this intent open a web browser to the link of our web app.
        Intent webview = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(webview);
    }

}
