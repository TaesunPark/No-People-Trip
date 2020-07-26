package org.androidtown.myapplication;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.androidtown.myapplication.Model.QRGeoModel;
import org.androidtown.myapplication.Model.QRURLModel;
import org.androidtown.myapplication.Model.QRVcardModel;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CameraActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        scannerView = (ZXingScannerView)findViewById(R.id.zxscan);
        txtResult = (TextView)findViewById(R.id.txt_result);
        //Request permission

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler((ZXingScannerView.ResultHandler) CameraActivity.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(CameraActivity.this,"You must accept this permission",Toast.LENGTH_SHORT);
                    }
                    
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }

    @Override
    protected void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {
        processRawResult(rawResult.getText());

        scannerView.startCamera();
    }

    private void processRawResult(String text) {
        if(text.startsWith("BEGIN:")){
            String[] tokens = text.split("\n");
            QRVcardModel qrVcardModel = new QRVcardModel();

            for(int i=0;i<tokens.length;i++){
                if(tokens[i].startsWith("BEGIN:")){
                    qrVcardModel.setType(tokens[i].substring("BEGIN:".length()));
                } else if(tokens[i].startsWith("N:")) {
                    qrVcardModel.setName(tokens[i].substring("N:".length()));
                } else if(tokens[i].startsWith("ORG:")) {
                    qrVcardModel.setName(tokens[i].substring("ORG:".length()));
                } else if(tokens[i].startsWith("TEL:")) {
                    qrVcardModel.setName(tokens[i].substring("TEL:".length()));
                } else if(tokens[i].startsWith("URL:")) {
                    qrVcardModel.setName(tokens[i].substring("URL:".length()));
                } else if(tokens[i].startsWith("EMAIL:")) {
                    qrVcardModel.setName(tokens[i].substring("EMAIL:".length()));
                } else if(tokens[i].startsWith("ADR:")) {
                    qrVcardModel.setName(tokens[i].substring("ADR:".length()));
                } else if(tokens[i].startsWith("NOTE:")) {
                    qrVcardModel.setName(tokens[i].substring("NOTE:".length()));
                } else if(tokens[i].startsWith("SUMMARY:")) {
                    qrVcardModel.setName(tokens[i].substring("SUMMARY:".length()));
                } else if(tokens[i].startsWith("DTSTART:")) {
                    qrVcardModel.setName(tokens[i].substring("DTSTART:".length()));
                } else if(tokens[i].startsWith("DTEND:")) {
                    qrVcardModel.setName(tokens[i].substring("DTEND:".length()));
                }
                txtResult.setText(qrVcardModel.getType());
            }
        }
        else if(text.startsWith("http://")||
                text.startsWith("https://")||
        text.startsWith("www.")){
            QRURLModel qrurlModel = new QRURLModel(text);
            txtResult.setText(qrurlModel.getUrl());
        } else if(text.startsWith("geo:")){
            QRGeoModel qrGeoModel = new QRGeoModel();
            String delims = "[ , ?q=]+";
            String tokens[] = text.split(delims);

            for(int i=0; i<tokens.length; i++){
                if(tokens[i].startsWith(" geo:")){
                    qrGeoModel.setLat(tokens[i].substring("geo:".length()));
                }
            }
            qrGeoModel.setLat(tokens[0].substring("geo:".length()));
            qrGeoModel.setLng(tokens[1]);
            qrGeoModel.setGeo_place(tokens[2]);
        }
    }
}
