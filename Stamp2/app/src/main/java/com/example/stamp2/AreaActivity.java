package com.example.stamp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//지역 선택
public class AreaActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
    }

    public void onClick_handler(View view)
    {
        Intent intent = new Intent(AreaActivity.this, CourseActivity.class);
        startActivity(intent);
    }
}
