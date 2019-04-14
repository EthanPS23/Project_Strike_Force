package com.example.travelexperts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailCustomerActivity extends AppCompatActivity {
    Button btnSignUp, btnClear;
    EditText etCustFirstName;
    // to do button sign up does an insert to the db,JSON?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);
        // these are initializing the buttons and the customer fields
        initializeCustFields();
        initializeCustBtns();

        // clear empties the edit texts, for now it is just the customer first name
        setCustBtnClear();
    }

    private void setCustBtnClear()
    {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextEdits();
            }
        });
    }

    private void initializeCustBtns()
    {
//     btnSignUp = findViewById(R.id.btnSignUp); this must insert a new customer and match password?
        btnClear = findViewById(R.id.btnClear);
    }

    private void initializeCustFields()
    {
        etCustFirstName = findViewById(R.id.etCustFirstName);
    }

    private void clearTextEdits()
    {
        etCustFirstName.setText("");
        // this is an example of how empty the edit text fields with a method
    }

}
