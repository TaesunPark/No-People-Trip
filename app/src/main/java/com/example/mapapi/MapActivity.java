package com.example.mapapi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.stamp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(37.549551, 127.075073);  //세종대학교 학생회관 위치
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("세종대학교");
        markerOptions.snippet("학생회관");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));

    }
}
