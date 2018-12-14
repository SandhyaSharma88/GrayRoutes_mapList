package com.example.sandhya.android.grayroutes_maplist.activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sandhya.android.grayroutes_maplist.R;
import com.example.sandhya.android.grayroutes_maplist.constants.MapDataContract;
import com.example.sandhya.android.grayroutes_maplist.objects.MapData;
import com.example.sandhya.android.grayroutes_maplist.objects.MapDataHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    @BindView(R.id.mapList_recyclerView)
    RecyclerView mapList_recyclerView;

    List<MapData> list = new ArrayList<>();

    private Double lat;
    private Double lon;

    Bitmap mapImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        //method for fetching data from db and mapping it to corresponding objects
        createMapDataList();

        //Setting and recycler view to display images and address
        MapDataAdapter mapDataAdapter = new MapDataAdapter(list);
        mapList_recyclerView.setAdapter(mapDataAdapter);

        mapList_recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<MapData> createMapDataList(){

        MapDataHelper db = new MapDataHelper(this);
        Cursor cursor = db.readMap();

        while (cursor.moveToNext()){
            Log.v("MapsActivity","MapData: " + cursor.getBlob(1) + " " + cursor.getDouble(2)
                    + " " + cursor.getDouble(3));

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> address = null;
            try {
                int index = cursor.getColumnIndex(MapDataContract.MapDataEntry._ID);
                int index2 = cursor.getColumnIndex(MapDataContract.MapDataEntry.COLUMN_IMAGE);
                int index3 = cursor.getColumnIndex(MapDataContract.MapDataEntry.COLUMN_LAT);
                int index4 = cursor.getColumnIndex(MapDataContract.MapDataEntry.COLUMN_LON);
                //int cid = cursor.getInt(index);

                byte[] image = cursor.getBlob(index2);
                Double lat = cursor.getDouble(index3);
                Double lon = cursor.getDouble(index4);

                address = geocoder.getFromLocation(lat,lon, 5);
                mapImage = BitmapFactory.decodeByteArray(image,0,image.length);

                MapData data = new MapData(mapImage, lat,lon, address);
                list.add(data);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG,"address is: " + address);
        }

        return list;
    }
}
