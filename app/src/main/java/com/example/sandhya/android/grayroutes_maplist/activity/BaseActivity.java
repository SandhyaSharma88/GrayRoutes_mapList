package com.example.sandhya.android.grayroutes_maplist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public void openMapsActivity() {
        Intent i = new Intent(BaseActivity.this, MapsActivity.class);
        startActivity(i);
    }

    public void openMapListActivity() {
        Intent i = new Intent(BaseActivity.this, ListActivity.class);
        startActivity(i);
    }
}
