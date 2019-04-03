//Author: Christopher Potvin
//Date: 4/2/2019
//About: This is the date source for the agent class. It calls the DB helper class to initialize the
// db connection string from the DBHelper class and creates a method for its context. It also contains
// an array list for retrieving agent information from the agent table by using a cursor that brings back
// data in the rows of the SQLite DB. Finally it has three methods for inserting, updating and deleting
// the agent object. These methods are utilized in the onclick event listeners in the detail activity.

package com.example.christopherpotvin_assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AgentDataSource {
    SQLiteDatabase db; // object for the SQLite DB
    DBHelper helper;

    public AgentDataSource(Context context)
    {
        helper = new DBHelper(context);
        helper.copyDatabase();
        db = helper.getWritableDatabase();
    }

    // method for storing agent data in an array list

    public ArrayList<Agent> getAllAgentInfo()
    {
        ArrayList<Agent> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Agents", null);
        while (cursor.moveToNext())
        {
            list.add(new Agent(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7)
            ));
        }
        cursor.close();
        return list;
    }

    // insert method

    public void insert(Agent agent)
    {
        ContentValues values = new ContentValues();
        values.put("AgentId", agent.getAgentId());
        values.put("AgtFirstName", agent.getAgtFirstName());
        values.put("AgtMiddleInitial", agent.getAgtMiddleInitial());
        values.put("AgtLastName", agent.getAgtLastName());
        values.put("AgtBusPhone", agent.getAgtBusPhone());
        values.put("AgtEmail", agent.getAgtEmail());
        values.put("AgtPosition", agent.getAgtPosition());
        values.put("AgencyId", agent.getAgencyId());
        long insertId = db.insert("Agents", null, values);

    }
    // update method

    public void update(Agent agent){
        ContentValues values = new ContentValues();
        values.put("AgentId", agent.getAgentId());
        values.put("AgtFirstName", agent.getAgtFirstName());
        values.put("AgtMiddleInitial", agent.getAgtMiddleInitial());
        values.put("AgtLastName", agent.getAgtLastName());
        values.put("AgtBusPhone", agent.getAgtBusPhone());
        values.put("AgtEmail", agent.getAgtEmail());
        values.put("AgtPosition", agent.getAgtPosition());
        values.put("AgencyId", agent.getAgencyId());

        String where = "AgentId=?";
        String [] whereArgs =  { agent.getAgentId() + ""};
        long updateId = db.update("Agents", values, where, whereArgs);
    }

    //delete method

    public void delete(Agent agent)
    {
        db.delete("Agents", "AgentId = " + agent.getAgentId(), null);
    }
}
