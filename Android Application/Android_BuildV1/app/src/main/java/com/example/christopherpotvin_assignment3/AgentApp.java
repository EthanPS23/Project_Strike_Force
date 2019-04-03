//Author: Christopher Potvin
//Date: 4/2/2019
//About: This is the agent application class that instantiates the following class, AgentDataSource.

package com.example.christopherpotvin_assignment3;

import android.app.Application;

public class AgentApp extends Application {
    AgentDataSource source;

    // generate the onCreate to create the AgentDataSource object

    @Override
    public void onCreate() {
        super.onCreate();
        source = new AgentDataSource(this);
    }

    public AgentDataSource getSource() {
        return source;
    }
}
