package com.example.mapmodule.model;

import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;

@Table(name = "Users")
public class User {

    public boolean isActive() {
        return this.isActive;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
    @PrimaryKey
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private boolean isActive;



    // Constructors, Getters, and Setters
}

