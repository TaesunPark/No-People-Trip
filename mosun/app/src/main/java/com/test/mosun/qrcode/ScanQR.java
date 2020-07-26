package com.test.mosun.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.test.mosun.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ScanQR extends AppCompatActivity {

    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        qrScan = new IntentIntegrator(this);
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
                    String name = obj.getString("name");
                    Log.v("qrcode contents",name);
                    //Toast.makeText(this, "Scanned: " + name, Toast.LENGTH_LONG).show();

                    //앱매니저 호출해서 스탬프 값 저장
                    //서버에 올려주기

                    finish();//화면 종료
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
}
