package com.example.travelexperts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Colors and design at the preliminary stage. Button colors and link colors can be changed as desired by the group

    Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to do login page, encryption or google sign in **

        //initialize the buttons method
        initializeBtns();

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
                Intent packageMain = new Intent(getApplicationContext(), PackageActivity.class);
                startActivity(packageMain);
            }
        });
    }

    public void instagramOpen(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
        startActivity(browserIntent);
    }
}
