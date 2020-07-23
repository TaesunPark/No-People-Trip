package com.example.stamp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import net.daum.android.map.MapViewEventListener;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity{

    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = new MapView(this);
//        //ViewGroup mapViewContainer = findViewById(R.id.map_view);
//        RelativeLayout mapViewContainer = (RelativeLayout)findViewById(R.id.map_view);
//        mapViewContainer.addView(mapView);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

    }
}
