package com.test.mosun.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.areaItem;
import com.test.mosun.login.LoginActivity;
import com.test.mosun.stamp.TourList;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        onSaveAreaData();
        onSaveTourListData();

    }

    /******Loading Data********/
    /*** 데이터 베이스에서 가져와서 추가 + 로컬에 일단 저장해놓고 변동되면 수정 하게 만들기**/

    public void onSaveAreaData() {
        ArrayList<areaItem> list;
        list = new ArrayList<>();
        list.add(new areaItem(0, "area_0", "서울"));
        list.add(new areaItem(0, "area_1", "경주"));
        AppManager.getInstance().setAreaList(list);
    }

    public void onSaveTourListData() {
        ArrayList<TourList> list;
        list = new ArrayList<>();
        list.add(new TourList("석굴암", "부처님 석상 기무띠", "500m 전", "12,341 명", R.drawable.ic_baseline_map_24));
        list.add(new TourList("석굴암", "부처님 석상 기무띠", "500m 전", "12,341 명", R.drawable.ic_baseline_map_24));
        list.add(new TourList("첨성대", "첨성대 기무띠", "700m 전", "123,421 명", R.drawable.ic_baseline_map_24));
        list.add(new TourList("강감찬 동상", "강감찬 기무띠", "800m 전", "124,341 명", R.drawable.ic_baseline_map_24));
        list.add(new TourList("강감찬 동상", "강감찬 기무띠", "800m 전", "124,341 명", R.drawable.ic_baseline_map_24));
        list.add(new TourList("강감찬 동상", "강감찬 기무띠", "800m 전", "124,341 명", R.drawable.ic_baseline_map_24));
        AppManager.getInstance().setTourList(list);

        finish();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

    }
}