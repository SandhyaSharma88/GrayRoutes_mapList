package com.example.sandhya.android.grayroutes_maplist.objects;

import android.graphics.Bitmap;
import android.location.Address;

import java.util.List;

//class to create Arraylist of data and prepare it for display  as a list

public class MapData {

    protected int id;
    protected Bitmap image;
    protected Double lat;
    protected Double lon;
    protected List<Address> address;

    public MapData(Bitmap image, Double lat, Double lon, List<Address> address) {
        this.image = image;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public List<Address> getAddress() {

        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
