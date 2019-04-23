package com.example.travelexperts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Colors and design at the preliminary stage. Button colors and link colors can be changed as desired by the group

    Button btnSignUp, btnLogin;
    EditText etEmail, etPassword;

    String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/LoginRESTService/login";

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
                etEmail = findViewById(R.id.etEmail);
                System.out.println(etEmail.getText());

                // String to connect to the REST Services
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // if login successful grant access to the packages page
                        if (response.equals("true")) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent packageMain = new Intent(getApplicationContext(), PackageActivity.class);
                            startActivity(packageMain);
                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect Login Details", Toast.LENGTH_LONG).show();
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

    public void instagramOpen(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.163.37.7:8080/TravelExpertsWebApp"));
//        10.163.37.119:8080/TravelExpertsWebApp/
        startActivity(browserIntent);
    }
}
