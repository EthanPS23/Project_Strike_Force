package com.example.travelexperts;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class DetailPackageActivity extends AppCompatActivity {
     Button btnOrder, btnBack, btnView;
     TextView tvpkgName, tvpkgStartDate,
             tvpkgDesc, tvpkgEndDate, tvpkgBasePrice;

    // To do these methods need to be created to show the package details based on the JSON info
    // Each img button will need a method or sql statement to call that specific package

    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_package);

        initializeBtns();
        initializeFields();

        rQueue = Volley.newRequestQueue(DetailPackageActivity.this);


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }

    private void jsonParse()
    {
        String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/packages/getallpackages";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(0);

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject packages = jsonArray.getJSONObject(i);

                                String pkgName = packages.getString("pkgName");
                                String pkgStartDate = packages.getString("pkgStartDate");
                                String pkgEndDate = packages.getString("pkgEndDate");
                                String pkgDesc = packages.getString("pkgDesc");
                                String pkgBasePrice = packages.getString("pkgBasePrice");

                                tvpkgName.append(pkgName);
                                tvpkgStartDate.append(pkgStartDate);
                                tvpkgEndDate.append(pkgEndDate);
                                tvpkgDesc.append(pkgDesc);
                                tvpkgBasePrice.append(pkgBasePrice);

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

    private void initializeBtns ()
    {
        btnOrder = findViewById(R.id.btnOrder);
        btnBack = findViewById(R.id.btnBack);
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

}
