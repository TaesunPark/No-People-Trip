package com.test.mosun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.mosun.information.Fragment_Reward;
import com.test.mosun.login.LoginActivity;
import com.test.mosun.map.Fragment_GoogleMap;
import com.test.mosun.map.Fragment_Map;
import com.test.mosun.qrcode.QRPopupActivity;
import com.test.mosun.stamp.Fragment_Stamp;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private Fragment_Home fragment_Home;
    private Fragment_GoogleMap fragment_Map;
    private Fragment_Stamp fragment_Stamp;
    private Fragment_Reward fragment_Reward;


    private CurveBottomBar curveBottomBar;
    FloatingActionButton floatingActionButton;
    BackPressCloseHandler backPressCloseHandler;


    private LoginActivity loginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backPressCloseHandler = new BackPressCloseHandler(this);
        setContentView(R.layout.activity_main);

        //loginActivity.finish();

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
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
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

                    Log.i("home","home");
                    if (fragment_Home == null) {
                        fragment_Home = Fragment_Home.newInstance();
                        fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Home, "home").commit();
                    }
                    if (fragment_Home != null)
                        fragmentManager.beginTransaction().attach(fragment_Home).commit();
                    if (fragment_Map != null)
                        fragmentManager.beginTransaction().hide(fragment_Map).commit();
                    if (fragment_Stamp != null)
                        fragmentManager.beginTransaction().detach(fragment_Stamp).commit();
                    if (fragment_Reward != null)
                        fragmentManager.beginTransaction().detach(fragment_Reward).commit();
                    break;
                case R.id.map:
                    if (fragment_Map == null) {
                        fragment_Map = fragment_Map.newInstance();
                        fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Map, "map").commit();
                    }
                    if (fragment_Home != null)
                        fragmentManager.beginTransaction().detach(fragment_Home).commit();
                    if (fragment_Map != null)
                        fragmentManager.beginTransaction().show(fragment_Map).commit();
                    if (fragment_Stamp != null)
                        fragmentManager.beginTransaction().detach(fragment_Stamp).commit();
                    if (fragment_Reward != null)
                        fragmentManager.beginTransaction().detach(fragment_Reward).commit();
                    break;
                case R.id.stamp_book:
                    if (fragment_Stamp == null) {
                        fragment_Stamp = fragment_Stamp.newInstance();
                        fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Stamp, "stamp").commit();
                    }
                    if (fragment_Home != null)
                        fragmentManager.beginTransaction().detach(fragment_Home).commit();
                    if (fragment_Map != null)
                        fragmentManager.beginTransaction().hide(fragment_Map).commit();
                    if (fragment_Stamp != null)
                        fragmentManager.beginTransaction().attach(fragment_Stamp).commit();
                    if (fragment_Reward != null)
                        fragmentManager.beginTransaction().detach(fragment_Reward).commit();
                    break;

                case R.id.information:
                    if (fragment_Reward == null) {
                        fragment_Reward = fragment_Reward.newInstance();
                        fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Reward, "reward").commit();
                    }
                    if (fragment_Home != null)
                        fragmentManager.beginTransaction().detach(fragment_Home).commit();
                    if (fragment_Map != null)
                        fragmentManager.beginTransaction().hide(fragment_Map).commit();
                    if (fragment_Stamp != null)
                        fragmentManager.beginTransaction().detach(fragment_Stamp).commit();
                    if (fragment_Reward != null)
                        fragmentManager.beginTransaction().attach(fragment_Reward).commit();
                    break;

            }
            return true;
        }
    }

    private View.OnClickListener floatingButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QRPopupActivity.class);
            startActivity(intent);

        }
    };
}

class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;
    private LoginActivity loginActivity;
    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            loginActivity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}