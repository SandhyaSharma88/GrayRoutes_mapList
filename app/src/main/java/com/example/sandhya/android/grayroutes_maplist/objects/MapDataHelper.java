package com.example.sandhya.android.grayroutes_maplist.objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sandhya.android.grayroutes_maplist.constants.MapDataContract;

//helper class to create, insert and read data from db

public class MapDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mapData.db";
    private static final int SCHEMA_VERSION = 1;

    public MapDataHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_MAP_TABLE = "CREATE TABLE "
                + MapDataContract.MapDataEntry.TABLE_NAME + " ("
                + MapDataContract.MapDataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MapDataContract.MapDataEntry.COLUMN_IMAGE + " BLOB, "
                + MapDataContract.MapDataEntry.COLUMN_LAT + " DOUBLE NOT NULL, "
                + MapDataContract.MapDataEntry.COLUMN_LON + " DOUBLE NOT NULL);";

        Log.v("MapsDataHelper", "");

        db.execSQL(SQL_CREATE_MAP_TABLE);

    }

    public void insertMap(byte[] image, double lat, double lon) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MapDataContract.MapDataEntry.COLUMN_IMAGE, image);
        values.put(MapDataContract.MapDataEntry.COLUMN_LAT, lat);
        values.put(MapDataContract.MapDataEntry.COLUMN_LON, lon);

        long newRowId = db.insert(MapDataContract.MapDataEntry.TABLE_NAME, null, values);

        Log.v("MapDataHelper", "New Row Id:" + newRowId);

    }

    public Cursor readMap() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = getReadableDatabase();

        String[] project = {MapDataContract.MapDataEntry._ID,
                MapDataContract.MapDataEntry.COLUMN_IMAGE,
                MapDataContract.MapDataEntry.COLUMN_LAT,
                MapDataContract.MapDataEntry.COLUMN_LON};

        Cursor cursor = db.query(MapDataContract.MapDataEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null);

        return cursor;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
