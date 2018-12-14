package com.example.sandhya.android.grayroutes_maplist.objects;

import android.graphics.Bitmap;
import android.location.Address;

import java.util.List;

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



    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Address> getAddress() {

        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public int getId() {

        return id;
    }

    public Bitmap getImage() {
        return image;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
