package com.test.mosun.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kakao.auth.Session;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.activity.AreaActivity;
import com.test.mosun.areaItem;
import com.test.mosun.data.LoginData;
import com.test.mosun.data.LoginResponse;
import com.test.mosun.network.RetrofitClient;
import com.test.mosun.network.ServiceApi;
import com.test.mosun.stamp.TourList;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/// 네이버 아이디로 로그인 샘플앱

/**
 * <br/> OAuth2.0 인증을 통해 Access Token을 발급받는 예제, 연동해제하는 예제,
 * <br/> 발급된 Token을 활용하여 Get 등의 명령을 수행하는 예제, 네아로 커스터마이징 버튼을 사용하는 예제 등이 포함되어 있다.
 *
 * @author naver
 */
public class LoginActivity extends Activity {

    private static final String TAG = "OAuthSampleActivity";

    /**
     * client 정보를 넣어준다.
     */
    private static String OAUTH_CLIENT_ID = "Y15miqwrgiKQChGv8TQh";
    private static String OAUTH_CLIENT_SECRET = "ym_wFloTHQ";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";

    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;

    /**
     * UI 요소들
     */
    private TextView mApiResultText;
    private static TextView mOauthAT;
    private static TextView mOauthRT;
    private static TextView mOauthExpires;

    private static TextView mOauthTokenType;
    private static TextView mOAuthState;

    private OAuthLoginButton mOAuthLoginButton;

    private ServiceApi service;
    private LoginData loginData;
    private SessionCallback sessionCallback = new SessionCallback();
    Session session;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //service = RetrofitClient.getClient().create(ServiceApi.class);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);
        mContext = this;

        initData();
        initView();

        //데이타 로딩
        onSaveAreaData();
        onSaveTourListData();


//        Button button = (Button) findViewById(R.id.nextButton);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                finish();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });

        mOAuthLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onButtonClick", "들어옴");
                mOAuthLoginInstance.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
                Log.i("onButtonClick", "들어옴");

                new RequestApiTask().execute();

//                finish();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                //startLogin(new LoginData(id,age,gender,email,name,birthday));
                //startLogin(loginData);
            }
        });
        this.setTitle("OAuthLoginSample Ver." + OAuthLogin.getVersion());

    }


    //ondestroy~onActivityResult >> 카카오 로그인
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void initData() {
        mOAuthLoginInstance = OAuthLogin.getInstance();

        mOAuthLoginInstance.showDevelopersLog(true);
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

        /*
         * 2015년 8월 이전에 등록하고 앱 정보 갱신을 안한 경우 기존에 설정해준 callback intent url 을 넣어줘야 로그인하는데 문제가 안생긴다.
         * 2015년 8월 이후에 등록했거나 그 뒤에 앱 정보 갱신을 하면서 package name 을 넣어준 경우 callback intent url 을 생략해도 된다.
         */
        //mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME, OAUTH_callback_intent_url);
    }

    private void initView() {
//        mApiResultText = (TextView) findViewById(R.id.api_result_text);
//
//        mOauthAT = (TextView) findViewById(R.id.oauth_access_token);
//        mOauthRT = (TextView) findViewById(R.id.oauth_refresh_token);
//        mOauthExpires = (TextView) findViewById(R.id.oauth_expires);
//        mOauthTokenType = (TextView) findViewById(R.id.oauth_type);
//        mOAuthState = (TextView) findViewById(R.id.oauth_state);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

        //updateView();
    }


    private void updateView() {
        mOauthAT.setText(mOAuthLoginInstance.getAccessToken(mContext));
        mOauthRT.setText(mOAuthLoginInstance.getRefreshToken(mContext));
        mOauthExpires.setText(String.valueOf(mOAuthLoginInstance.getExpiresAt(mContext)));
        mOauthTokenType.setText(mOAuthLoginInstance.getTokenType(mContext));
        mOAuthState.setText(mOAuthLoginInstance.getState(mContext).toString());
    }

    @Override
    protected void onResume() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();

    }

    /**
     * startOAuthLoginActivity() 호출시 인자로 넘기거나, OAuthLoginButton 에 등록해주면 인증이 종료되는 걸 알 수 있다.
     */
    @SuppressLint("HandlerLeak")
    static private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                String refreshToken = mOAuthLoginInstance.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                String tokenType = mOAuthLoginInstance.getTokenType(mContext);
                //mOauthAT.setText(accessToken);
                //mOauthRT.setText(refreshToken);
                //mOauthExpires.setText(String.valueOf(expiresAt));
                //mOauthTokenType.setText(tokenType);
                //mOAuthState.setText(mOAuthLoginInstance.getState(mContext).toString());
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }

    };


//    public void onButtonClick(View v) throws Throwable {
//
//        switch (v.getId()) {
//            case R.id.buttonOAuthLoginImg: {
//                mOAuthLoginInstance.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
//                Log.i("onButtonClick","들어옴");
//                new RequestApiTask().execute();
//
//                break;
//            }
//            case R.id.nextButton: {
//                Log.i("nextButton","들어옴");
//                Intent intent = new Intent(LoginActivity.this, AreaActivity.class);
//                startActivity(intent);
//                break;
//            }
////            case R.id.buttonVerifier: {
////                new RequestApiTask().execute();
////                //startLogin(loginData);
////                break;
////            }
////            case R.id.buttonRefresh: {
////                new RefreshTokenTask().execute();
////                break;
////            }
////            case R.id.buttonOAuthLogout: {
////                mOAuthLoginInstance.logout(mContext);
////                updateView();
////                break;
////            }
////            case R.id.buttonOAuthDeleteToken: {
////                new DeleteTokenTask().execute();
////                break;
////            }
//            default:
//                break;
//        }
//    }


    private class DeleteTokenTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            boolean isSuccessDeleteToken = mOAuthLoginInstance.logoutAndDeleteToken(mContext);

            if (!isSuccessDeleteToken) {
                // 서버에서 token 삭제에 실패했어도 클라이언트에 있는 token 은 삭제되어 로그아웃된 상태이다
                // 실패했어도 클라이언트 상에 token 정보가 없기 때문에 추가적으로 해줄 수 있는 것은 없음
                Log.d(TAG, "errorCode:" + mOAuthLoginInstance.getLastErrorCode(mContext));
                Log.d(TAG, "errorDesc:" + mOAuthLoginInstance.getLastErrorDesc(mContext));
            }

            return null;
        }

        protected void onPostExecute(Void v) {
            updateView();
        }
    }

    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            //mApiResultText.setText((String) "");
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = mOAuthLoginInstance.getAccessToken(mContext);
            String data = mOAuthLoginInstance.requestApi(mContext, at, url);
            try {
                JSONObject result = new JSONObject(data);
                String id = result.getJSONObject("response").getString("id");
                String age = result.getJSONObject("response").getString("age");
                String gender = result.getJSONObject("response").getString("gender");
                String email = result.getJSONObject("response").getString("email");
                String name = result.getJSONObject("response").getString("name");
                String birthday = result.getJSONObject("response").getString("birthday");

                Log.i("id", id);
                Log.i("age", age);
                Log.i("gender", gender);
                Log.i("email", email);
                Log.i("name", name);
                Log.i("birthday", birthday);

                startLogin(new LoginData(id, age, gender, email, name, birthday));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mOAuthLoginInstance.requestApi(mContext, at, url);
        }

        protected void onPostExecute(String content) {
            //mApiResultText.setText((String) content);
        }
    }

    private class RefreshTokenTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return mOAuthLoginInstance.refreshAccessToken(mContext);
        }

        protected void onPostExecute(String res) {
            updateView();
        }
    }


    private void startLogin(LoginData data) {


        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                //Log.i("userId",Integer.toString(result.getUserId()));

                Log.i("startLogin", "존재하지 않는 계정");
                if (result.getMessage().startsWith("존재하지"))//존재하지 않는 계정이면 insert
                {
                    Log.i("startLogin", "들어왔쥬");
                    startInsert(data);
                }

                if (Integer.toString(result.getUserId()).equals(data.getUserId())) {
                    Log.i("startLogin", "같음" + result.getUserId());
                    AppManager.getInstance().setUserID(Integer.toString(result.getUserId()));
                    goToNextActivity();
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }

    private void startInsert(LoginData data) {


        service.userInsert(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("userId", Integer.toString(result.getUserId()));
                // Log.i("startLogin","존재하지 않는 계정");


            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }

    public void goToNextActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

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
//        TourList data = new TourList("석굴암", "부처님 석상 기무띠", "500m 전","12,341 명",R.drawable.ic_baseline_map_24);
//        //int imageResourceID, String tourTitle, String tourDescription, String distance, String numericalValue, int imageNumericalValueID
//        adapter.addItem(data);
//        data = new TourList("석굴암", "부처님 석상 기무띠", "500m 전","12,341 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
//        data = new TourList("첨성대", "첨성대 기무띠", "700m 전","123,421 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
//        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
//        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
//        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
//        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
//        data = new TourList("강감찬 동상", "강감찬 기무띠", "800m 전","124,341 명",R.drawable.ic_baseline_map_24);
//        adapter.addItem(data);
    }


}
