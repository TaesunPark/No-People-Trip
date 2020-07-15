package org.androidtown.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

        tourList = new TourList();
        stamp = new Stamp();
        myPage = new MyPage();
        map = new Map();
        camera = new Camera();
    }

    private void setFrag(int pageNumber){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (pageNumber){

            case MainActivity.CAMERAPAGE:
                ft.replace(R.id.main_frame, camera);
                ft.commit();
                break;

            case MainActivity.STAMPPAGE:
                ft.replace(R.id.main_frame, stamp);
                ft.commit();
                break;

            case MainActivity.MAPPAGE:
                ft.replace(R.id.main_frame, map);
                ft.commit();
                break;

            case MainActivity.MYPAGE:
                ft.replace(R.id.main_frame, myPage);
                ft.commit();
                break;

        }
    }
}