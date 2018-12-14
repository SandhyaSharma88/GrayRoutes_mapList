package com.example.sandhya.android.grayroutes_maplist.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.sandhya.android.grayroutes_maplist.R;
import com.example.sandhya.android.grayroutes_maplist.objects.MapDataHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnCameraIdleListener, GoogleMap.OnMapLongClickListener {

    private static final String TAG = "MapsActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @BindView(R.id.tap_text)
    TextView mTapTextView;

    @BindView(R.id.camera_text)
    TextView mCameraTextView;

    //@BindView(R.id.map_image)
  //  ImageView mImageView;

    private GoogleMap mMap;
    byte[] imageArray;
    private Double latitude;
    private Double longitude;
    private LatLng latLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);
    }

    @Override
    public void onCameraIdle() {
        // mCameraTextView.setText(mMap.getCameraPosition().toString());
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.i(TAG, "tapped, point=" + latLng);

        latitude = latLng.latitude;
        longitude = latLng.longitude;
        latLong = latLng;
        takePictureIntent();

    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra("exit_on_sent", true);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
          //  mImageView.setImageBitmap(imageBitmap);

            imageArray = bitmapToByteArray(imageBitmap);
        }

        MapDataHelper db = new MapDataHelper(this);
        db.insertMap(imageArray,latitude, longitude);

        addMarker(latLong);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Log.i(TAG, "long pressed, point=" + latLng);

        takePictureIntent();
        //performAction(latLng);
        addMarker(latLng);
    }

    private void addMarker(LatLng latLng){

        String snippet = String.format(Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Dropped pin").snippet(snippet));
    }
}
