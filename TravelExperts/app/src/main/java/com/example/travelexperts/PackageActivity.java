package com.example.travelexperts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PackageActivity extends AppCompatActivity {
    ImageButton myImageButtonPackage1, myImageButtonPackage2, myImageButtonPackage3, myImageButtonPackage4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        initializeImgBtns(); // this method is for initializing the buttons by their res id

        getPackageDetails(); // this method has the onclick listeners for each img button and populates the package details
        // that has been selected according to which one was selected. To do -> need to grab JSON from jsp to do so

    }

    private void initializeImgBtns ()
    {
        myImageButtonPackage1 = findViewById(R.id.imgBtnCNYear);
        myImageButtonPackage2 = findViewById(R.id.imgBtnPolynesia);
        myImageButtonPackage3 = findViewById(R.id.imgBtnAsia);
        myImageButtonPackage4 = findViewById(R.id.imgBtnEuro);
    }

    private void getPackageDetails()
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
    }

}
