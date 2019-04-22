package com.example.travelexperts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DetailCustomerActivity extends AppCompatActivity {
    Button btnCreateNewCust, btnClear;
    EditText etEmail, etPassword;

    // to do button sign up does an insert to the db,JSON?
    String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/LoginRESTService/register";

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
                        Toast.makeText(DetailCustomerActivity.this, "Some error occurred -> " + error, Toast.LENGTH_LONG).show();
                        ;

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("CustEmail", etEmail.getText().toString());
                        parameters.put("CustPassword", etPassword.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(DetailCustomerActivity.this);
                requestQueue.add(request);
                request.setRetryPolicy(new DefaultRetryPolicy(
                        100,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));
            }
        });

        btnCreateNewCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(DetailCustomerActivity.this, MainActivity.class);
                startActivity(mainActivity);
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

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    private void clearTextEdits() {

        // this is an example of how empty the edit text fields with a method
    }

}
