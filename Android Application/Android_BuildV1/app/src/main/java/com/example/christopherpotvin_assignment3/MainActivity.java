//Author: Christopher Potvin
//Date: 4/2/2019
//About: This is the main activity that populates a simple list view with the Agent class with the help
//of an array adapter and creates a new intent for this particular application context to pass to the
//detail activity when the agent is clicked. .

package com.example.christopherpotvin_assignment3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    //Reference the objects on the main activity xml page
    ListView lvAgentInfo;
    AgentDataSource source;
    AgentApp app;
    ArrayAdapter<Agent> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (AgentApp) getApplication();
        source = app.getSource();

        lvAgentInfo = findViewById(R.id.lvAgentInfo);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, source.getAllAgentInfo());
        lvAgentInfo.setAdapter(adapter);
        lvAgentInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("agent", adapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.clear();
        adapter.addAll(source.getAllAgentInfo());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(source.getAllAgentInfo());
    }
}
