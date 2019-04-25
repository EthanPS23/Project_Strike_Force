package com.example.travelexperts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailCustomerActivity extends AppCompatActivity {
    Button btnCreateNewCust, btnClear;
    EditText etCustFirstName, etCustLastName, etCustAddress, etCustCity, etCustCountry, etCustProv,
        etCustPostal, etCustHomePhone, etCustBusPhone, etEmail, etPassword;
    Spinner spCustCountry, spCustProvState;

    // to do button sign up does an insert to the db,JSON?
    String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/Register/register";
    // Change this to RegisterRESTService/register

    ArrayList<Countries> dataCountries = new ArrayList<>();

    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);
        // these are initializing the buttons and the customer fields
        initializeCustFields();
        initializeCustBtns();
        setCustBtnClear();

        rQueue = Volley.newRequestQueue(DetailCustomerActivity.this);
        addItemsOnSpinner1();

        spCustCountry = (Spinner) findViewById(R.id.spCustCountry);
        spCustCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addItemsOnSpinner2(String.valueOf(spCustCountry.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //addListenerOnSpinnerItemSelection();

        btnCreateNewCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            Toast.makeText(DetailCustomerActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent mainActivity = new Intent(DetailCustomerActivity.this, MainActivity.class);
                            startActivity(mainActivity);

                        } else {
                            //Toast.makeText(DetailCustomerActivity.this, "Sorry but registration was not successful", Toast.LENGTH_LONG).show();
                            Toast.makeText(DetailCustomerActivity.this, response, Toast.LENGTH_SHORT).show();
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
                        /*parameters.put("CustProv", etCustProv.getText().toString());
                        parameters.put("CustCountry", etCustCountry.getText().toString());*/
                        parameters.put("CustProv", String.valueOf(spCustProvState.getSelectedItem()));
                        parameters.put("CustCountry", String.valueOf(spCustCountry.getSelectedItem()));
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
        //etCustCountry = findViewById(R.id.etCustCountry);
        //etCustProv = findViewById(R.id.etCustProv);
        etCustPostal = findViewById(R.id.etCustPostal);
        etCustHomePhone = findViewById(R.id.etCustHomePhone);
        etCustBusPhone = findViewById(R.id.etCustBusPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

    }

    private void clearTextEdits() {

    }

    public void addListenerOnSpinnerItemSelection() {
        spCustCountry = (Spinner) findViewById(R.id.spCustCountry);
        spCustProvState = (Spinner) findViewById(R.id.spCustProvState);
        spCustCountry.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        addItemsOnSpinner2(String.valueOf(spCustCountry.getSelectedItem()));
    }

    public void addItemsOnSpinner1(){
        /*spCustCountry = (Spinner) findViewById(R.id.spCustCountry);
        List<String> list = new ArrayList<String>();
        list.add("CA");
        list.add("US");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spCustCountry.setAdapter(dataAdapter);*/

        CountryJson();
        List<String> list = new ArrayList<String>();
        for (Countries c : dataCountries) {
            list.add(c.getCountryId());
        }
//                    *//*HashMap<String, String> map = new HashMap<>();
//                    map.put("pkgName", p.getPkgName() + "");
//            map.put("pkgStartDate", p.getPkgStartDate());
//            map.put("pkgEndDate", p.getPkgEndDate());
//            map.put("pkgDesc", p.getPkgDesc());
//            map.put("pkgBasePrice", p.getPkgBasePrice() + "");
//            packageMaps.add(map);*//*

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spCustCountry.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner2(String countryId){
        spCustProvState = (Spinner) findViewById(R.id.spCustProvState);
        List<String> list = new ArrayList<String>();
        if (countryId.equals("CA")){
            list.add("AB");
            list.add("BC");
            list.add("MB");
            list.add("NB");
            list.add("NL");
            list.add("NS");
            list.add("NT");
            list.add("NU");
            list.add("ON");
            list.add("PE");
            list.add("QC");
            list.add("SK");
            list.add("YT");
        }
        else if(countryId.equals("US")){
            list.add("AK");
            list.add("AL");
            list.add("AR");
            list.add("AZ");
            list.add("CA");
            list.add("CO");
            list.add("CT");
            list.add("DC");
            list.add("DE");
            list.add("FL");
            list.add("GA");
            list.add("HI");
            list.add("IA");
            list.add("ID");
            list.add("IL");
            list.add("IN");
            list.add("KS");
            list.add("KY");
            list.add("LA");
            list.add("MA");
            list.add("ME");
            list.add("MI");
            list.add("MN");
            list.add("MO");
            list.add("MS");
            list.add("MT");
            list.add("NC");
            list.add("ND");
            list.add("NE");
            list.add("NH");
            list.add("NV");
            list.add("NY");
            list.add("OH");
            list.add("OK");
            list.add("OR");
            list.add("PA");
            list.add("RI");
            list.add("SC");
            list.add("SD");
            list.add("TN");
            list.add("TX");
            list.add("UT");
            list.add("VA");
            list.add("VI");
            list.add("VT");
            list.add("WA");
            list.add("WI");
            list.add("WV");
            list.add("WY");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spCustProvState.setAdapter(dataAdapter);
    }

    public void CountryJson(){
        //final ArrayList<Package> data = new ArrayList<>();
        String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/packages/getallpackages";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject =response.getJSONObject(0);
                            JSONArray jsonArray = jsonObject.getJSONArray("countries");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject countries = jsonArray.getJSONObject(i);

                                //String

                                /*int PackageId = packages.getInt("packageId");
                                String pkgName = packages.getString("pkgName");
                                String pkgStartDate = packages.getString("pkgStartDate");
                                String pkgEndDate = packages.getString("pkgEndDate");
                                String pkgDesc = packages.getString("pkgDesc");
                                String pkgImg = packages.getString("pkgImg");
                                double pkgBasePrice = packages.getDouble("pkgBasePrice");
                                double pkgAgencyCommission = packages.getDouble("pkgAgencyCommission");*/

                                dataCountries.add(new Countries(countries.getString("countryId"),
                                        countries.getString("countryName")));

                                /*data.add(new Package(packages.getInt("packageId"), packages.getString("pkgName"),
                                        packages.getString("pkgStartDate"), packages.getString("pkgEndDate"),
                                        packages.getString("pkgDesc"), packages.getString("pkgImg"),
                                        packages.getDouble("pkgBasePrice"), packages.getDouble("pkgAgencyCommission")));*/

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //stffs();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("RVA", "error:" + error);

                String errorCode = "nothing";

                if (error instanceof TimeoutError) {
                    errorCode = "Time out";
                } else if (error instanceof NoConnectionError) {
                    errorCode = "No connection";
                } else if (error instanceof AuthFailureError) {
                    errorCode = "Authentication failure";
                } else if (error instanceof ServerError) {
                    errorCode = "Server error";
                } else if (error instanceof NetworkError) {
                    errorCode = "Network error";
                } else if (error instanceof ParseError) {
                    errorCode = "Error";
                }
                Toast.makeText(DetailCustomerActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();
            }
        });

        rQueue.add(request);
        //return data;
    }

    /*private void stffs(){
        ArrayList<HashMap<String, String>> packageMaps = new ArrayList<>();
        for (Countries c : dataCountries){
            HashMap<String, String> map = new HashMap<>();
            map.put("CountryId", c.getCountryId());
            map.pu
            *//*HashMap<String, String> map = new HashMap<>();
            map.put("pkgName", p.getPkgName() + "");
            map.put("pkgStartDate", p.getPkgStartDate());
            map.put("pkgEndDate", p.getPkgEndDate());
            map.put("pkgDesc", p.getPkgDesc());
            map.put("pkgBasePrice", p.getPkgBasePrice() + "");
            packageMaps.add(map);*//*
        }

        int resource = R.layout.package_item;
        String[] from = {"pkgName", "pkgStartDate", "pkgEndDate", "pkgDesc", "pkgBasePrice"};
        int[] to = {R.id.tvPkgNameItem, R.id.tvPkgStartDateItem, R.id.tvPkgEndDateItem, R.id.tvPkgDescItem, R.id.tvPkgBasePriceItem};
        SimpleAdapter adapter = new SimpleAdapter(this, packageMaps, resource, from, to);
        lvPackage.setAdapter(adapter);
    }*/
}
