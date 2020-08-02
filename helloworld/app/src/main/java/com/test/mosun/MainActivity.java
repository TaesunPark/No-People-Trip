package com.test.mosun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.test.mosun.home.Fragment_Home;
import com.test.mosun.information.Fragment_Reward;
import com.test.mosun.login.LoginActivity;
import com.test.mosun.map.Fragment_GoogleMap;
import com.test.mosun.utility.CurveBottomBar;
import com.test.mosun.qrcode.QRPopupActivity;
import com.test.mosun.stamp.Fragment_Stamp;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private Fragment_Home fragment_Home= Fragment_Home.newInstance();
    private Fragment_GoogleMap fragment_Map= Fragment_GoogleMap.newInstance();
    private Fragment_Stamp fragment_Stamp= Fragment_Stamp.newInstance();
    private Fragment_Reward fragment_Reward= Fragment_Reward.newInstance();

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
                    replaceFragment(fragment_Home);
                    break;

                case R.id.map:
                    replaceFragment(fragment_Map);
                    break;

                case R.id.stamp_book:
                    replaceFragment(fragment_Stamp);
                    break;

                case R.id.reward:
                    replaceFragment(fragment_Reward);
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

    //fragment 전환하는 메소드
    public void replaceFragment(Fragment fragment){
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();  // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    public void replaceAndGetBundleFragment(Fragment fragment, Fragment bundleFragment)
    {

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();  // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

    }
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