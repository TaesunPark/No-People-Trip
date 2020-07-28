package com.test.mosun.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.test.mosun.AppManager;
import com.test.mosun.R;
import com.test.mosun.data.LoginData;
import com.test.mosun.data.LoginResponse;
import com.test.mosun.data.QRData;
import com.test.mosun.data.QRResponse;
import com.test.mosun.login.LoginActivity;
import com.test.mosun.network.RetrofitClient;
import com.test.mosun.network.ServiceApi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//하하하하하
public class ScanQR extends AppCompatActivity {

    private IntentIntegrator qrScan;
    private ServiceApi service;
    private Activity thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        qrScan = new IntentIntegrator(this);
        qrScan.setBeepEnabled(false);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setPrompt("관광지의 QR코드를 스캔하세요.");
        qrScan.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(result.getContents());
                    String qr_id = obj.getString("qr_id");
                    String qr_name = obj.getString("qr_name");
                    Double qr_longitude = obj.getDouble("qr_longitude");
                    Double qr_latitude = obj.getDouble("qr_latitude");

                    Log.i("qr_id",qr_id);
                    Log.i("qr_name",qr_name);
                    Log.i("qr_longitude",Double.toString(qr_longitude));
                    Log.i("qr_latitude",Double.toString(qr_latitude));

                    //Toast.makeText(this, "Scanned: " + name, Toast.LENGTH_LONG).show();

                    //앱매니저 호출해서 스탬프 값 저장

                    //서버에 qrnum 올리기
//                    checkAndUploadQRNum(new QRData(qr_id));
                    finish();//화면 종료
                    //goToActivity(new QRData(qr_id));
                    checkAndUploadQRNum(new QRData(qr_id));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    private void goToActivity(QRData data)
    {
        Intent intent = new Intent(thisActivity, QRNetworkActivity.class);
        intent.putExtra("qrdata",data);
        startActivity(intent);
    }
    private void checkAndUploadQRNum(QRData data) {

        service.qrScan(data).enqueue(new Callback<QRResponse>() {
            @Override
            public void onResponse(Call<QRResponse> call, Response<QRResponse> response) {
                QRResponse result = response.body();
                //Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("qr_id",result.getQRID());
                // Log.i("startLogin","존재하지 않는 계정");


            }


            @Override
            public void onFailure(Call<QRResponse> call, Throwable t) {
                //Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("qr스캔 에러 발생", t.getMessage());

            }
        });
    }

}
