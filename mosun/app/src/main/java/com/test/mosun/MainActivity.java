package com.test.mosun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.mosun.R;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private Fragment_Home fragment_Home;

    private CurveBottomBar curveBottomBar;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //상태 바 색 바꿔줌
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FAFAFA"));
        setContentView(R.layout.activity_main);
        AppManager.getInstance().setMainActivity(this);

        // 초기 프래그먼트 설정
        fragment_Home = Fragment_Home.newInstance();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment_Home).commit();


        // 하단 메뉴 설정
        floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(floatingButtonClick);
        AppManager.getInstance().setMenuFloatingActionButton(floatingActionButton);

        curveBottomBar = findViewById(R.id.customBottomBar);
        curveBottomBar.inflateMenu(R.menu.navigation);
        curveBottomBar.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    @Override
    protected void onStop() {
        // 강제 종료시 onDestroy() 호출이 안됨
        // 서버에 저장하는 함수 만들기
        Log.d("테스트", "mainActivity onStop");
        super.onStop();
    }

    // 하단 메뉴 선택 리스너
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.home:
                    if (fragment_Home == null) {
                        fragment_Home = Fragment_Home.newInstance();
                        fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Home, "home").commit();
                    }
                    if (fragment_Home != null)
                        fragmentManager.beginTransaction().attach(fragment_Home).commit();
//                    if (fragment_Map != null)
//                        fragmentManager.beginTransaction().hide(fragment_Map).commit();
//                    if (fragment_Plant_Book != null)
//                        fragmentManager.beginTransaction().detach(fragment_Plant_Book).commit();
//                    if (fragment_Information != null)
//                        fragmentManager.beginTransaction().detach(fragment_Information).commit();
                    break;
                case R.id.map:

                    break;

            }
            return true;
        }
    }

    private View.OnClickListener floatingButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //qr코드 메소드

        }
    };
}