package org.androidtown.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    MyAdapter adapter;
    public static final int CAMERAPAGE = 0;
    public static final int STAMPPAGE = 1;
    public static final int MAPPAGE = 2;
    public static final int MYPAGE = 3;

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Camera camera;
    private Map map;
    private MyPage myPage;
    private Stamp stamp;
    private TourList tourList;
    private ArrayList<TourList> dataList;
    private CheckBox checkBox;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getData();
        makeBottomNavigationVar();
        //바텀 네비게이션 바


    }

    private void setFrag(int pageNumber){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (pageNumber){

            case MainActivity.CAMERAPAGE:
                ft.replace(R.id.dataList, camera);
                ft.commit();
                break;

            case MainActivity.STAMPPAGE:
                ft.replace(R.id.dataList, stamp);
                ft.commit();
                break;

            case MainActivity.MAPPAGE:
                ft.replace(R.id.dataList, map);
                ft.commit();
                break;

            case MainActivity.MYPAGE:
                ft.replace(R.id.dataList, myPage);
                ft.commit();
                break;

        }
    }

    public void makeBottomNavigationVar(){
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_camera:
                        setFrag(MainActivity.CAMERAPAGE);
                        break;
                    case R.id.action_stamp:
                        setFrag(MainActivity.STAMPPAGE);
                        break;
                    case R.id.action_map:
                        setFrag(MainActivity.MAPPAGE);
                        break;
                    case R.id.action_myPage:
                        setFrag(MainActivity.MYPAGE);
                        break;
                }
                return true;
            }
        });
        check = false;
        tourList = new TourList();
        stamp = new Stamp();
        myPage = new MyPage();
        map = new Map();
        camera = new Camera();

    }

    private void init(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dataList);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void getData()
    {
        TourList data = new TourList("석굴암", "부처님 석상 기무띠", "500m 전","12,341 명",R.drawable.ic_baseline_map_24);
        //int imageResourceID, String tourTitle, String tourDescription, String distance, String numericalValue, int imageNumericalValueID
        adapter.addItem(data);
        data = new TourList("석굴암", "부처님 석상 기무띠", "500m 전","12,341 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
        data = new TourList("첨성대", "첨성대 기무띠", "700m 전","123,421 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
        adapter.addItem(data);
    }

}