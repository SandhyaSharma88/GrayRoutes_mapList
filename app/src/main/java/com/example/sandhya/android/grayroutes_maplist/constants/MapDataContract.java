package com.example.sandhya.android.grayroutes_maplist.constants;

import android.provider.BaseColumns;

public final class MapDataContract {

    private MapDataContract() {
    }

    public static final class MapDataEntry implements BaseColumns {

        public static final String TABLE_NAME = "MAPDATALIST";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LON = "lon";
    }
}
