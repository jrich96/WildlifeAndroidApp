package com.example.jaker.wildlife2;

import java.io.Serializable;

/**
 * Created by jaker on 06/05/2017.
 */

public class Document implements Serializable
{

    private int id;
    private String name;
    private String category;
    private String activity;
    private String location;
    private int amount;

    public Document()
    {

    }

    public Document(String name, String category, String activity, String location, int amount, int id)
    {
        this.name = name;
        this.category = category;
        this.activity = activity;
        this.location = location;
        this.amount = amount;
        this.id = id;
    }

    public Document(String name, String category, String activity, String location, int amount)
    {
        this.name = name;
        this.category = category;
        this.activity = activity;
        this.location = location;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
