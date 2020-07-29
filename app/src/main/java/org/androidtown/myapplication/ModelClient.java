package org.androidtown.myapplication;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ModelClient extends AppCompatActivity implements View.OnClickListener {
    private float avgTemp;
    private float minTemp;
    private float maxTemp;
    private float rainFall;
    private float holiday;
    private float jan;
    private float feb;
    private float mar;
    private float apr;
    private float may;
    private float jun;
    private float jul;
    private float aug;
    private float sep;
    private float oct;
    private float nov;
    private float dec;
    public static final String TAG = "ModelClient";
    private TextView tv_output;
    private TextView tv_output2;
    private TextView tv_output3;
    private TextView tv_output4;
    private Button btn_compute;
    private Interpreter interpreter = null;
    private Interpreter interpreter1 = null;
    private Interpreter interpreter2 = null;
    private Interpreter interpreter3 = null;

    public ModelClient(){
        avgTemp = 0;
        minTemp = 0;
        maxTemp = 0;
        rainFall = 0;
        holiday = 1;
        jan = 0;
        feb = 0;
        mar = 0;
        apr = 0;
        may = 1;
        jun = 0;
        jul = 0;
        aug = 0;
        sep = 0;
        oct = 0;
        nov = 0;
        dec = 0;
    }

    public ModelClient(float avgTemp, float minTemp, float maxTemp, float rainFall, float holiday, float jan, float feb, float mar, float apr, float may, float jun, float jul, float aug, float sep, float oct, float nov, float dec){
        avgTemp = this.avgTemp;
        minTemp = this.minTemp;
        maxTemp = this.maxTemp;
        rainFall = this.rainFall;
        holiday = this.holiday;
        jan = this.jan;
        feb = this.feb;
        mar = this.mar;
        apr = this.apr;
        may = this.may;
        jun = this.jun;
        aug = this.aug;
        sep = this.sep;
        oct = this.oct;
        nov = this.nov;
        dec = this.dec;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tensorflow);
        tv_output = findViewById(R.id.tv_output);
        tv_output2 = findViewById(R.id.tv_output2);
        tv_output3 = findViewById(R.id.tv_output3);
        tv_output4 = findViewById(R.id.tv_output4);
        btn_compute = findViewById(R.id.btn_compute);
        btn_compute.setOnClickListener(this);

        try {
            interpreter = new Interpreter(loadModel(getAssets(), "kgoung.tflite",interpreter));
            interpreter1 = new Interpreter(loadModel(getAssets(), "dgoung.tflite",interpreter1));
            interpreter2 = new Interpreter(loadModel(getAssets(), "ckgoung.tflite",interpreter2));
            interpreter3 = new Interpreter(loadModel(getAssets(), "cdgoung.tflite",interpreter3));

            float[] inputVal = new float[17];
            inputVal[0] = avgTemp;
            inputVal[1] = minTemp;
            inputVal[2] = maxTemp;
            inputVal[3] = rainFall;
            inputVal[4] = holiday;
            inputVal[5] = jan;
            inputVal[6] = feb;
            inputVal[7] = mar;
            inputVal[8] = apr;
            inputVal[9] = may;
            inputVal[10] = jun;
            inputVal[11] = jul;
            inputVal[12] = aug;
            inputVal[13] = sep;
            inputVal[14] = oct;
            inputVal[15] = nov;
            inputVal[16] = dec;
            float[][] outputVal = new float[1][1];
            interpreter.run(inputVal, outputVal);
            float inferredValue = outputVal[0][0];
            tv_output.setText(String.valueOf(inferredValue));
            interpreter1.run(inputVal, outputVal);
            float inferredValue1 = outputVal[0][0];
            tv_output2.setText(String.valueOf(inferredValue1));
            interpreter2.run(inputVal, outputVal);
            float inferredValue2 = outputVal[0][0];
            tv_output3.setText(String.valueOf(inferredValue2));
            interpreter3.run(inputVal, outputVal);
            float inferredValue3 = outputVal[0][0];
            tv_output4.setText(String.valueOf(inferredValue3));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    MappedByteBuffer loadModel(AssetManager assest, String modelFile, Interpreter interpreter) throws IOException {
        AssetFileDescriptor fileDescriptor = assest.openFd(modelFile);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        interpreter = new Interpreter(buffer);
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    @Override
    public void onClick(View view) {

    }

}