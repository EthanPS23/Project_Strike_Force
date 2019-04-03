//Author: Christopher Potvin
//Date: 4/2/2019
//About: This is the detail activity class that updates, deletes, and saves agent data based on which button is clicked.
//There are a few methods that deal with the enabling and disabling of the button controls. Also initializes the elements
//of the detail_activity xml page so they can be programmatically used in this class.

package com.example.christopherpotvin_assignment3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends Activity {
    Agent agent;
    EditText etAgentId, etAgtFirstName, etAgtMiddleInitial, etAgtLastName,
            etAgtBusPhone, etAgtEmail, etAgtPosition, etAgencyId;
    Button btnInsert, btnEdit, btnSave, btnDelete;
    String buttonMode = "view"; //object for the switch case to with the views
    AgentDataSource source;
    AgentApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        app = (AgentApp) getApplication();
        source = app.getSource();

        //set up the objects on the activity detail, all the edit texts
        etAgentId = findViewById(R.id.etAgentId);
        etAgtFirstName = findViewById(R.id.etAgtFirstName);
        etAgtMiddleInitial = findViewById(R.id.etAgtMiddleInitial);
        etAgtLastName = findViewById(R.id.etAgtLastName);
        etAgtBusPhone = findViewById(R.id.etAgtBusPhone);
        etAgtEmail = findViewById(R.id.etAgtEmail);
        etAgtPosition = findViewById(R.id.etAgtPosition);
        etAgencyId = findViewById(R.id.etAgencyId);

        // initiate the buttons
        btnInsert = findViewById(R.id.btnInsert);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);

        // disable the edit text fields on load of this activity + the save button
        disableFieldsAndSaveBtn();

        Intent intent = getIntent();
        agent = (Agent) intent.getSerializableExtra("agent");
        fillFields(agent); // create a method to fill the fields

        // create a method for the insert button
        // have to create an onClick listener
        btnInsert.setOnClickListener(new View.OnClickListener() {
            // this can be shortcut to (v) -> {}
            @Override
            public void onClick(View v) {
                // method to enable the edit text fields
                enableFields();

                // setting all the edit texts to empty on insert

                etAgentId.setText("");
                etAgtFirstName.setText("");
                etAgtMiddleInitial.setText("");
                etAgtLastName.setText("");
                etAgtBusPhone.setText("");
                etAgtEmail.setText("");
                etAgtPosition.setText("");
                etAgencyId.setText("");

                enableSave(); // enable save button but disable the three other buttons when insert is clicked

                buttonMode = "insert"; // string for the button mode will be insert called from the onSave button
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableSave(); // enable the save button on the edit button but disable the other three buttons when edit is clicked
                buttonMode = "update"; // string for the button mode will be update called from the onSave button
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                source.delete(agent);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is a switch case to switch through the string "views"
                switch (buttonMode) {
                    case "insert":
                        source.insert(new Agent(0, etAgtFirstName.getText().toString(),
                                etAgtMiddleInitial.getText().toString(), etAgtLastName.getText().toString(),
                                etAgtBusPhone.getText().toString(), etAgtEmail.getText().toString(),
                                etAgtPosition.getText().toString(), Integer.parseInt(etAgencyId.getText().toString())));
                        break;

                    case "update":
                        source.update(new Agent(Integer.parseInt(etAgentId.getText().toString()),
                                etAgtFirstName.getText().toString(), etAgtMiddleInitial.getText().toString(),
                                etAgtLastName.getText().toString(), etAgtBusPhone.getText().toString(),
                                etAgtEmail.getText().toString(), etAgtPosition.getText().toString(),
                                Integer.parseInt(etAgencyId.getText().toString())));
                        break;
                }
                buttonMode = "view";
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
    //method for filling out the edit text fields on the onCreate based on which agent
    // object was clicked in the main activity

    private void fillFields(Agent agent) {
        etAgentId.setText(agent.getAgentId() + "");
        etAgtFirstName.setText(agent.getAgtFirstName());
        etAgtMiddleInitial.setText(agent.getAgtMiddleInitial());
        etAgtLastName.setText(agent.getAgtLastName());
        etAgtBusPhone.setText(agent.getAgtBusPhone());
        etAgtEmail.setText(agent.getAgtEmail());
        etAgtPosition.setText(agent.getAgtPosition());
        etAgencyId.setText(agent.getAgencyId() + "");
    }

    // method for disabling the controls on the creation of the detail activity --> onCreate
    private void disableFieldsAndSaveBtn ()
    {
        etAgentId.setEnabled(false);
        etAgtFirstName.setEnabled(false);
        etAgtMiddleInitial.setEnabled(false);
        etAgtLastName.setEnabled(false);
        etAgtBusPhone.setEnabled(false);
        etAgtEmail.setEnabled(false);
        etAgtPosition.setEnabled(false);
        etAgencyId.setEnabled(false);
        btnSave.setEnabled(false);
    }

    // method for enabling the controls on the creation of the detail activity --> onInsert and onEdit

    private void enableFields ()
    {
        etAgentId.setEnabled(true);
        etAgtFirstName.setEnabled(true);
        etAgtMiddleInitial.setEnabled(true);
        etAgtLastName.setEnabled(true);
        etAgtBusPhone.setEnabled(true);
        etAgtEmail.setEnabled(true);
        etAgtPosition.setEnabled(true);
        etAgencyId.setEnabled(true);
    }

    //method for disabling three buttons except for the save on the edit
    private void enableSave ()
    {
        btnSave.setEnabled(true);
        btnInsert.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
    }
}
