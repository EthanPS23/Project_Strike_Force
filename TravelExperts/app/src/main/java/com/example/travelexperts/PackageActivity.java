package com.example.travelexperts;

//import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PackageActivity extends AppCompatActivity {
    //ImageButton myImageButtonPackage1, myImageButtonPackage2, myImageButtonPackage3, myImageButtonPackage4;
    ListView lvPackage;
    ArrayList<Package> data = new ArrayList<>();

    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        
        lvPackage = findViewById(R.id.lvPackages);
        rQueue = Volley.newRequestQueue(PackageActivity.this);
        getPackages();
        //final ArrayList<Package> packages = getPackages();

        /*ArrayList<HashMap<String, String>> packageMaps = new ArrayList<>();
        for (Package p : data){
            HashMap<String, String> map = new HashMap<>();
            map.put("pkgName", p.getPkgName() + "");
            map.put("pkgStartDate", p.getPkgStartDate());
            map.put("pkgEndDate", p.getPkgEndDate());
            map.put("pkgDesc", p.getPkgDesc());
            map.put("pkgBasePrice", p.getPkgBasePrice() + "");
            packageMaps.add(map);
        }

        int resource = R.layout.package_item;
        String[] from = {"pkgName", "pkgStartDate", "pkgEndDate", "pkgDesc", "pkgBasePrice"};
        int[] to = {R.id.tvPkgNameItem, R.id.tvPkgStartDateItem, R.id.tvPkgEndDateItem, R.id.tvPkgDescItem, R.id.tvPkgBasePriceItem};
        SimpleAdapter adapter = new SimpleAdapter(this, packageMaps, resource, from, to);
        lvPackage.setAdapter(adapter);*/

        lvPackage.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Package pkg = data.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailPackageActivity.class);
                intent.putExtra("pkg", pkg);
                startActivity(intent);
            }
        });

        /*initializeImgBtns(); // this method is for initializing the buttons by their res id

        getPackageDetails(); // this method has the onclick listeners for each img button and populates the package details
        // that has been selected according to which one was selected. To do -> need to grab JSON from jsp to do so*/

    }
    private void stffs(){
        ArrayList<HashMap<String, String>> packageMaps = new ArrayList<>();
        for (Package p : data){
            HashMap<String, String> map = new HashMap<>();
            map.put("pkgName", p.getPkgName() + "");
            map.put("pkgStartDate", p.getPkgStartDate());
            map.put("pkgEndDate", p.getPkgEndDate());
            map.put("pkgDesc", p.getPkgDesc());
            map.put("pkgBasePrice", p.getPkgBasePrice() + "");
            packageMaps.add(map);
        }

        int resource = R.layout.package_item;
        String[] from = {"pkgName", "pkgStartDate", "pkgEndDate", "pkgDesc", "pkgBasePrice"};
        int[] to = {R.id.tvPkgNameItem, R.id.tvPkgStartDateItem, R.id.tvPkgEndDateItem, R.id.tvPkgDescItem, R.id.tvPkgBasePriceItem};
        SimpleAdapter adapter = new SimpleAdapter(this, packageMaps, resource, from, to);
        lvPackage.setAdapter(adapter);
    }

    //Author Chris Potvin, rewritten by Ethan Shipley
    //private ArrayList<Package> getPackages()
    private void getPackages()
    {
        //final ArrayList<Package> data = new ArrayList<>();
        String URL = "http://10.163.37.7:8080/TravelExpertsWebApp/rest/packages/getallpackages";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject =response.getJSONObject(0);
                            JSONArray jsonArray = jsonObject.getJSONArray("packages");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject packages = jsonArray.getJSONObject(i);

                                int PackageId = packages.getInt("packageId");
                                String pkgName = packages.getString("pkgName");
                                String pkgStartDate = packages.getString("pkgStartDate");
                                String pkgEndDate = packages.getString("pkgEndDate");
                                String pkgDesc = packages.getString("pkgDesc");
                                String pkgImg = packages.getString("pkgImg");
                                double pkgBasePrice = packages.getDouble("pkgBasePrice");
                                double pkgAgencyCommission = packages.getDouble("pkgAgencyCommission");

                                data.add(new Package(packages.getInt("packageId"), packages.getString("pkgName"),
                                        packages.getString("pkgStartDate"), packages.getString("pkgEndDate"),
                                        packages.getString("pkgDesc"), packages.getString("pkgImg"),
                                        packages.getDouble("pkgBasePrice"), packages.getDouble("pkgAgencyCommission")));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        stffs();
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
                Toast.makeText(PackageActivity.this, "This is the error-> " +errorCode, Toast.LENGTH_LONG).show();
            }
        });

        rQueue.add(request);
        //return data;
    }

    /*private ArrayList<Package> getPackages() {
        ArrayList<Package> data = new ArrayList<>();
        data.add(new Package(1,"Carribean","2016-12-12 00:00:00",
                "2016-12-20 00:00:00","8 Day All Inclusive Hawaiian Vacation",
                "Images/Polynesia.jpg",3000.00,310.00));
        data.add(new Package(1,"New Year","2016-12-12 00:00:00",
                "2016-12-20 00:00:00","8 Day All Inclusive Hawaiian Vacation",
                "Images/Polynesia.jpg",3000.00,310.00));
        data.add(new Package(1,"Russia","2016-12-12 00:00:00",
                "2016-12-20 00:00:00","8 Day All Inclusive Hawaiian Vacation",
                "Images/Polynesia.jpg",3000.00,310.00));
        data.add(new Package(1,"Australia","2016-12-12 00:00:00",
                "2016-12-20 00:00:00","8 Day All Inclusive Hawaiian Vacation",
                "Images/Polynesia.jpg",3000.00,310.00));
        return data;
    }*/

    /*private void initializeImgBtns ()
    {
        *//*myImageButtonPackage1 = findViewById(R.id.imgBtnCNYear);
        myImageButtonPackage2 = findViewById(R.id.imgBtnPolynesia);
        myImageButtonPackage3 = findViewById(R.id.imgBtnAsia);
        myImageButtonPackage4 = findViewById(R.id.imgBtnEuro);*//*
    }*/

    /*private void getPackageDetails()
    {
        myImageButtonPackage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadFirstPackage = new Intent(PackageActivity.this, DetailPackageActivity.class);
                startActivity(intentLoadFirstPackage);
            }
        });

        myImageButtonPackage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadSecondPackage = new Intent(PackageActivity.this, DetailPackageActivity.class);
                startActivity(intentLoadSecondPackage);
            }
        });

        myImageButtonPackage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadThirdPackage = new Intent(PackageActivity.this, DetailPackageActivity.class);
                startActivity(intentLoadThirdPackage);
            }
        });

        myImageButtonPackage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadFourthPackage = new Intent(PackageActivity.this, DetailPackageActivity.class);
                startActivity(intentLoadFourthPackage);
            }
        });
    }*/

}
