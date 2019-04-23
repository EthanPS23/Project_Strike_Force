package com.example.travelexperts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

public class DetailCustomerActivity extends AppCompatActivity {
    Button btnCreateNewCust, btnClear;
    EditText etCustFirstName, etCustLastName, etCustAddress, etCustCity, etCustCountry, etCustProv,
        etCustPostal, etCustHomePhone, etCustBusPhone, etEmail, etPassword;

    // to do button sign up does an insert to the db,JSON?
    String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/Register/register";
    // Change this to RegisterRESTService/register

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);
        // these are initializing the buttons and the customer fields
        initializeCustFields();
        initializeCustBtns();
        setCustBtnClear();

        btnCreateNewCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            Toast.makeText(DetailCustomerActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            Intent mainActivity = new Intent(DetailCustomerActivity.this, MainActivity.class);
                            startActivity(mainActivity);

                        } else {
                            Toast.makeText(DetailCustomerActivity.this, "Sorry but registration was not successful", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(DetailCustomerActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("CustFirstName", etCustFirstName.getText().toString());
                        parameters.put("CustLastName", etCustLastName.getText().toString());
                        parameters.put("CustAddress", etCustAddress.getText().toString());
                        parameters.put("CustCity", etCustCity.getText().toString());
                        parameters.put("CustProv", etCustProv.getText().toString());
                        parameters.put("CustCountry", etCustCountry.getText().toString());
                        parameters.put("CustPostal", etCustPostal.getText().toString());
                        parameters.put("CustHomePhone", etCustHomePhone.getText().toString());
                        parameters.put("CustBusPhone", etCustBusPhone.getText().toString());
                        parameters.put("CustEmail", etEmail.getText().toString());
                        parameters.put("CustPassword", etPassword.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(DetailCustomerActivity.this);
                requestQueue.add(request);

            }
        });

    }

    private void setCustBtnClear() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextEdits();
            }
        });
    }

    private void initializeCustBtns() {
        btnCreateNewCust = findViewById(R.id.btnCreateNewCust);
        btnClear = findViewById(R.id.btnClear);
    }

    private void initializeCustFields() {
        etCustFirstName = findViewById(R.id.etCustFirstName);
        etCustLastName = findViewById(R.id.etCustLastName);
        etCustAddress = findViewById(R.id.etCustAddress);
        etCustCity = findViewById(R.id.etCustCity);
        etCustCountry = findViewById(R.id.etCustCountry);
        etCustProv = findViewById(R.id.etCustProv);
        etCustPostal = findViewById(R.id.etCustPostal);
        etCustHomePhone = findViewById(R.id.etCustHomePhone);
        etCustBusPhone = findViewById(R.id.etCustBusPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    private void clearTextEdits() {

        // this is an example of how empty the edit text fields with a method
    }

}
