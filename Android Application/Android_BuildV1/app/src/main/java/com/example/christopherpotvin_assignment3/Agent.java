//Author: Christopher Potvin
//Date: 4/2/2019
//About: This is the agent class that corresponds to the Agent columns in the Travel Experts database.
//it is declaring the fields, generating the constructor, and the getters/setters of the agent.
//Further, a toString method is created at the bottom of the class and this is what prints out to the list
//view in the main activity.


package com.example.christopherpotvin_assignment3;

import java.io.Serializable;

public class Agent implements Serializable {
    // Declare my fields
    private int AgentId; // int for Agent
    private String AgtFirstName, AgtMiddleInitial, AgtLastName,
    AgtBusPhone, AgtEmail, AgtPosition; // string fields for Agent
    private int AgencyId; // int for Agency

    // Build the constructor and pass the parameters
    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone,
                 String agtEmail, String agtPosition, int agencyId) {
        AgentId = agentId;
        this.AgtFirstName = agtFirstName;
        this.AgtMiddleInitial = agtMiddleInitial;
        this.AgtLastName = agtLastName;
        this.AgtBusPhone = agtBusPhone;
        this.AgtEmail = agtEmail;
        this.AgtPosition = agtPosition;
        AgencyId = agencyId;
    }

    //generate getters and setters, specify this for everything but int types

    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getAgtFirstName() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        this.AgtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.AgtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.AgtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.AgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.AgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.AgtPosition = agtPosition;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }

    // toString method that will generate the string to show in the list view of the main activity

    @Override
    public String toString() {
        return  AgtFirstName + " " + AgtLastName;
    }
}
