package com.test.mosun.loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.data.LoginData;
import com.test.mosun.data.LoginResponse;
import com.test.mosun.data.QRData;
import com.test.mosun.data.QRResponse;
import com.test.mosun.data.modelResponse;
import com.test.mosun.home.areaItem;
import com.test.mosun.login.LoginActivity;
import com.test.mosun.network.NetworkActivity;
import com.test.mosun.network.NetworkStatus;
import com.test.mosun.network.RetrofitClient;
import com.test.mosun.network.ServiceApi;
import com.test.mosun.stamp.TourList;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoadingActivity extends AppCompatActivity {

    private float avgTemp;
    private float minTemp;
    private float maxTemp;
    private float confirmedCorona=-1;

    private Interpreter kungbokgoungInterpreter = null;
    private Interpreter ducksugoungInterpreter = null;
    private Interpreter changkunggoungInterpreter = null;
    private Interpreter changduckgoungInterpreter = null;

    private Interpreter sunrungInterpreter = null;
    private Interpreter jeongrungInterpreter = null;
    private Interpreter taerungInterpreter = null;
    private Interpreter uirungInterpreter = null;
    private Interpreter hunrungInterpreter = null;
    private Interpreter younghwiwonInterpreter = null;

    // ??????
    private Interpreter gyeonggijeonInterpreter = null;
    private Interpreter nationalMuseumInjeonjuInterpreter = null;
    private Interpreter sparacuaInterpreter = null;
    private Interpreter zooInJeonjuInterpreter = null;
    private Interpreter hanbyukInterpreter = null;
    private Interpreter railBikeInterpreter = null;
    private Interpreter arboretumInterpreter = null;
    // ??????
    private Interpreter kangcheonMountainInterpreter = null;
    private Interpreter fruitVilageInterpreter = null;
    private Interpreter pepperVilageInterpreter = null;
    private Interpreter jangruInterpreter = null;
    private Interpreter mountainMuseumInterpreter = null;
    private Interpreter fermentationSauceInterpreter = null;
    // ??????

    private float sunrungPridictionNumber;
    private float jeongrungPridictionNumber;
    private float taerungPridictionNumber;
    private float uirungPridictionNumber;
    private float hunrungPridictionNumber;
    private float younghwiwonPridictionNumber;

    private float kungbokgoungPridictionNumber;
    private float ducksugoungPridictionNumber;
    private float changkunggoungPridictionNumber;
    private float changduckgoungPridictionNumber;

    private float gyeonggijeonPridictionNumber;
    private float nationalMuseumInjeonjuPridictionNumber;
    private float sparacuaPridictionNumber;
    private float zooInJeonjuPridictionNumber;
    private float hanbyukPridictionNumber;
    private float railBikePridictionNumber;
    private float arboretumPridictionNumber;

    private float kangcheonMountainPridictionNumber;
    private float fruitVilagePridictionNumber;
    private float pepperVilagePridictionNumber;
    private float jangruPridictionNumber;
    private float mountainMuseumPridictionNumber;
    private float fermentationSaucePridictionNumber;

    ArrayList<TourList> tourList = null;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        //onclearData(); //????????? ?????????
        ExecutorService es = Executors.newSingleThreadExecutor();
            es.execute(this::getCoronaInfoData);

        es.execute(this::getWeatherInfoData);
        es.execute(new Runnable() {
            @Override
            public void run() {
                if (confirmedCorona == -1) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    initTensorflow();
                } else {
                    initTensorflow();
                }
            }
        });
        es.execute(this::checkData);
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    getQRNumData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        es.execute(this::getNetworkConnection);
        es.shutdown();
    }


    /***** ???????????? ???????????? ???????????? *****/
    private void getNetworkConnection() {
        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {
            onSaveAreaData();
            getLoginData();
        } else {
            finish();
            Intent intent = new Intent(getApplicationContext(), NetworkActivity.class);
            startActivity(intent);
        }
    }

    /***** ?????? ?????? ???????????? *****/
    private void getLoginData() {
        SharedPreferences sp = getSharedPreferences("NPT", MODE_PRIVATE);
        String loginId = sp.getString("user_id", null);

        if (loginId != null) {
            Log.i("?????? loginId", loginId);
            startLogin(new LoginData(loginId));
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Log.i("?????? loginId", "null");
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }


    /**
     * ?????? ?????? ????????? ??????
     */
    public void onclearData() {

        Log.i("??????", "????????? ?????? ??????");
        SharedPreferences sp = getSharedPreferences("NPT", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }


    /******Loading New Data********/
    /*** ????????? ??????????????? ???????????? ?????? + ????????? ?????? ??????????????? ???????????? ?????? ?????? ?????????**/
    public void onSaveAreaData() {
        ArrayList<areaItem> list;
        list = new ArrayList<>();
        list.add(new areaItem(0, "area_0", "??????","????????? ?????? ?????? ???????????????"));
        list.add(new areaItem(0, "area_1", "??????","??? ?????? ?????? ?????? ????????? ???????????????"));
        list.add(new areaItem(0, "area_2", "??????","????????? ??????????????? ???????????????"));
        list.add(new areaItem(0, "area_2", "??????","?????? ???, ?????? ????????? ????????? ????????????"));
        list.add(new areaItem(0, "area_2", "??????","????????? ?????? ????????? ???????????????"));
        list.add(new areaItem(0, "area_2", "??????","?????? ????????? ???????????? ???????????? ???????????????"));

        AppManager.getInstance().setAreaList(list);
    }


    public void onSaveTourListData() {
        Log.i("onSaveTourListData", "onSaveTourListData");

        ArrayList<TourList> listSeoul;
        ArrayList<TourList> listJeonju;
        ArrayList<TourList> listSunchang;
        //??????
        listSeoul = new ArrayList<>();
        listJeonju = new ArrayList<>();
        listSunchang = new ArrayList<>();

        listSeoul.add(new TourList("?????????", "??????", 37.5792642, 126.9778535, kungbokgoungPridictionNumber, 0, R.drawable.kgoung, R.drawable.viewpager_icon2));
        Log.d("?????????", String.valueOf(kungbokgoungPridictionNumber));
        listSeoul.add(new TourList("?????????", "??????", 37.5657008, 126.9740246, ducksugoungPridictionNumber, 0, R.drawable.dgoung, R.drawable.ic_ducksu));
        listSeoul.add(new TourList("?????????", "??????", 37.5787708, 126.9926811, changkunggoungPridictionNumber, 0, R.drawable.image_03, R.drawable.viewpager_icon2));
        listSeoul.add(new TourList("?????????", "??????", 37.5808977, 126.9898217, changduckgoungPridictionNumber, 0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listSeoul.add(new TourList("??????", "??????", 37.5029079, 127.0168782, sunrungPridictionNumber, 0, R.drawable.image_05, R.drawable.ic_neung));
        listSeoul.add(new TourList("??????", "??????", 37.4603954, 126.9269338, jeongrungPridictionNumber, 0, R.drawable.image_06, R.drawable.ic_neung));
        listSeoul.add(new TourList("??????", "??????", 37.4604794, 127.0495097, hunrungPridictionNumber, 0, R.drawable.image_07, R.drawable.ic_neung));
        listSeoul.add(new TourList("??????", "??????", 37.6038265, 127.0225271, taerungPridictionNumber, 0, R.drawable.image_08, R.drawable.ic_neung));
        listSeoul.add(new TourList("??????", "??????", 37.6038317, 127.0553579, uirungPridictionNumber, 0, R.drawable.image_09, R.drawable.ic_neung));
        listSeoul.add(new TourList("?????????", "??????", 37.5885055, 127.0414405, younghwiwonPridictionNumber, 0, R.drawable.image_10, R.drawable.ic_neung));

        listJeonju.add(new TourList("?????????", "??????", 35.8153224,127.1476037, gyeonggijeonPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listJeonju.add(new TourList("?????????????????????", "??????", 35.8012972,127.0875554, nationalMuseumInjeonjuPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listJeonju.add(new TourList("???????????????", "??????", 35.8174308,127.1134445, sparacuaPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listJeonju.add(new TourList("?????? ?????????", "??????", 35.8555198,127.1424744, zooInJeonjuPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listJeonju.add(new TourList("???????????????", "??????", 35.8120443,127.1562016, hanbyukPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listJeonju.add(new TourList("?????????????????????", "??????", 35.829531,127.1739387, railBikePridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listJeonju.add(new TourList("???????????????????????????", "??????", 35.870964,127.0521402, arboretumPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));

        //??????
        listSunchang.add(new TourList("?????????", "??????", 35.4087639,127.065336 , kangcheonMountainPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listSunchang.add(new TourList("??????????????????", "??????", 35.3192685,127.0533413, fruitVilagePridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listSunchang.add(new TourList("?????????????????????", "??????", 35.4833446,127.1144903, pepperVilagePridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listSunchang.add(new TourList("???????????????", "??????", 35.3657696,127.1061715, jangruPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listSunchang.add(new TourList("???????????????", "??????", 35.4788907,126.9227723, mountainMuseumPridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));
        listSunchang.add(new TourList("??????????????????", "??????", 35.3673458,127.1006084, fermentationSaucePridictionNumber,0, R.drawable.cdkoung, R.drawable.viewpager_icon2));

        Collections.sort(listSeoul, new SortListByPredictNumber());
        Collections.sort(listSunchang, new SortListByPredictNumber());
        Collections.sort(listJeonju, new SortListByPredictNumber());

        AppManager.getInstance().setTourList(listSeoul);
        AppManager.getInstance().setJeonjuList(listJeonju);
        AppManager.getInstance().setSunchangList(listSunchang);

    }

    private void checkData() {
        SharedPreferences prefs = getSharedPreferences("NPT", Context.MODE_PRIVATE);
        AppManager.getInstance().setuserSns(prefs.getString("userSns", ""));
        Log.i("??????", "userSns(main) " + AppManager.getInstance().getuserSns());
        if (prefs.contains("?????????")) {

            ExecutorService es = Executors.newSingleThreadExecutor();
            es.submit(this::onSaveTourListData);
            es.submit(this::loadData);
            es.shutdown();
            Log.i("?????? checkData", "o");
        } else {
            ExecutorService es = Executors.newSingleThreadExecutor();
            es.submit(this::onSaveTourListData);
            es.submit(this::saveData);
            es.shutdown();
            Log.i("?????? checkData", "x");

        }

    }

    private void saveData() {

        Log.i("?????? saveData", "saveData");

        ArrayList<TourList> list = AppManager.getInstance().getTourList();

        SharedPreferences prefs = getSharedPreferences("NPT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        for (TourList item : list) {
            editor.putString(item.getTourTitle(), Boolean.toString(item.isCollected()));
        }

        editor.apply();
    }

    @SuppressLint("LongLogTag")
    private void loadData() {
        Log.i("?????? loadData(loading)", "loadData");

        ArrayList<TourList> list = AppManager.getInstance().getTourList();
        SharedPreferences prefs = getSharedPreferences("NPT", Context.MODE_PRIVATE);

        for (TourList item : list) {
            String isCollected = prefs.getString(item.getTourTitle(), "");
            Log.i("?????? loadData(loading)", isCollected);
            item.setCollected(Boolean.parseBoolean(isCollected));

        }


        AppManager.getInstance().setTourList(list);

        AppManager.getInstance().setStampCount(Integer.parseInt(prefs.getString("stampCount", "")));
        AppManager.getInstance().setMaskCount(Integer.parseInt(prefs.getString("maskCount", "")));
        Log.i("??????", "stampCount(loading) " + AppManager.getInstance().getStampCount());

    }


    private void initTensorflow() {
        try {
            kungbokgoungInterpreter = new Interpreter(loadModel(getAssets(), "kgoung.tflite", kungbokgoungInterpreter));
            ducksugoungInterpreter = new Interpreter(loadModel(getAssets(), "dgoung.tflite", ducksugoungInterpreter));
            changkunggoungInterpreter = new Interpreter(loadModel(getAssets(), "ckgoung.tflite", changkunggoungInterpreter));
            changduckgoungInterpreter = new Interpreter(loadModel(getAssets(), "cdgoung.tflite", changduckgoungInterpreter));

            sunrungInterpreter = new Interpreter(loadModel(getAssets(), "sunrung.tflite", sunrungInterpreter));
            jeongrungInterpreter = new Interpreter(loadModel(getAssets(), "jeongrung.tflite", jeongrungInterpreter));
            taerungInterpreter = new Interpreter(loadModel(getAssets(), "taerung.tflite", taerungInterpreter));
            uirungInterpreter = new Interpreter(loadModel(getAssets(), "uirung.tflite", uirungInterpreter));
            hunrungInterpreter = new Interpreter(loadModel(getAssets(), "hunrung.tflite", hunrungInterpreter));
            younghwiwonInterpreter = new Interpreter(loadModel(getAssets(), "youngHwiWon.tflite", younghwiwonInterpreter));
            // ?????? ???,???
            gyeonggijeonInterpreter = new Interpreter(loadModel(getAssets(), "jeonjujunlast.tflite", gyeonggijeonInterpreter));
            nationalMuseumInjeonjuInterpreter = new Interpreter(loadModel(getAssets(), "jeonjumuseumlast.tflite", nationalMuseumInjeonjuInterpreter));
            sparacuaInterpreter = new Interpreter(loadModel(getAssets(), "jeonjuspalast.tflite", sparacuaInterpreter));
            zooInJeonjuInterpreter = new Interpreter(loadModel(getAssets(), "jeonjuzoolast.tflite", zooInJeonjuInterpreter));
            hanbyukInterpreter = new Interpreter(loadModel(getAssets(), "jeonjuhanbyeoklast.tflite", hanbyukInterpreter));
            railBikeInterpreter = new Interpreter(loadModel(getAssets(), "jeonjubikelast.tflite", railBikeInterpreter));
            arboretumInterpreter = new Interpreter(loadModel(getAssets(), "jeonjuarboretumlast.tflite", arboretumInterpreter));

            kangcheonMountainInterpreter = new Interpreter(loadModel(getAssets(), "sunchangmountainlast.tflite", kangcheonMountainInterpreter));
            fruitVilageInterpreter = new Interpreter(loadModel(getAssets(), "sunchangvilagelast.tflite", fruitVilageInterpreter));
            pepperVilageInterpreter = new Interpreter(loadModel(getAssets(), "sunchangpepperlast.tflite", pepperVilageInterpreter));
            jangruInterpreter = new Interpreter(loadModel(getAssets(), "sunchangjangrulast.tflite", jangruInterpreter));
            mountainMuseumInterpreter = new Interpreter(loadModel(getAssets(), "sunchangmuseumlast.tflite", mountainMuseumInterpreter));
            fermentationSauceInterpreter = new Interpreter(loadModel(getAssets(), "sunchangsourcelast.tflite", fermentationSauceInterpreter));

            float[] inputVal = new float[4];
            inputVal[0] = avgTemp;
            inputVal[1] = minTemp;
            inputVal[2] = maxTemp;
            inputVal[3] = confirmedCorona;

            Log.i("??????", "inputVal[0] : " + inputVal[0]);
            Log.i("??????", "inputVal[1] : " + inputVal[1]);
            Log.i("??????", "inputVal[2] : " + inputVal[2]);
            Log.i("??????", "inputVal[3] : " + inputVal[3]);

            float[][] outputVal = new float[1][1];

            kungbokgoungInterpreter.run(inputVal, outputVal);
            kungbokgoungPridictionNumber = outputVal[0][0];

            ducksugoungInterpreter.run(inputVal, outputVal);
            ducksugoungPridictionNumber = outputVal[0][0];

            changkunggoungInterpreter.run(inputVal, outputVal);
            changkunggoungPridictionNumber = outputVal[0][0];

            changduckgoungInterpreter.run(inputVal, outputVal);
            changduckgoungPridictionNumber = outputVal[0][0];

            sunrungInterpreter.run(inputVal, outputVal);
            sunrungPridictionNumber = outputVal[0][0];

            hunrungInterpreter.run(inputVal, outputVal);
            hunrungPridictionNumber = outputVal[0][0];

            taerungInterpreter.run(inputVal, outputVal);
            taerungPridictionNumber = outputVal[0][0];

            jeongrungInterpreter.run(inputVal, outputVal);
            jeongrungPridictionNumber = outputVal[0][0];

            uirungInterpreter.run(inputVal, outputVal);
            uirungPridictionNumber = outputVal[0][0];

            younghwiwonInterpreter.run(inputVal, outputVal);
            younghwiwonPridictionNumber = outputVal[0][0];

            sunrungInterpreter.run(inputVal, outputVal);
            sunrungPridictionNumber = outputVal[0][0];


            gyeonggijeonInterpreter.run(inputVal,outputVal);
            gyeonggijeonPridictionNumber = outputVal[0][0];

            nationalMuseumInjeonjuInterpreter.run(inputVal,outputVal);
            nationalMuseumInjeonjuPridictionNumber = outputVal[0][0];
            sparacuaInterpreter.run(inputVal,outputVal);
            sparacuaPridictionNumber = outputVal[0][0];
            zooInJeonjuInterpreter.run(inputVal,outputVal);
            zooInJeonjuPridictionNumber = outputVal[0][0];
            hanbyukInterpreter.run(inputVal,outputVal);
            hanbyukPridictionNumber = outputVal[0][0];
            railBikeInterpreter.run(inputVal,outputVal);
            railBikePridictionNumber =outputVal[0][0];
            arboretumInterpreter.run(inputVal,outputVal);
            arboretumPridictionNumber = outputVal[0][0];
            kangcheonMountainInterpreter.run(inputVal,outputVal);
            kangcheonMountainPridictionNumber = outputVal[0][0];
            fruitVilageInterpreter.run(inputVal,outputVal);
            fruitVilagePridictionNumber = outputVal[0][0];
            pepperVilageInterpreter.run(inputVal,outputVal);
            pepperVilagePridictionNumber = outputVal[0][0];
            jangruInterpreter.run(inputVal,outputVal);
            jangruPridictionNumber = outputVal[0][0];
            mountainMuseumInterpreter.run(inputVal,outputVal);
            mountainMuseumPridictionNumber = outputVal[0][0];
            fermentationSauceInterpreter.run(inputVal,outputVal);
            fermentationSaucePridictionNumber = outputVal[0][0];

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ???????????? ?????? ????????????
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

    private void getQRNumData() throws InterruptedException {
        //qr_num ????????????
        ArrayList<TourList> list = AppManager.getInstance().getTourList();
        for (int i = 0; i < list.size(); i++) {
            Thread.sleep(200);
            getQRNum(new QRData(list.get(i).getTourTitle()), i);

        }
    }

    protected void getQRNum(QRData data, int i) {

        final int[] qr_num = new int[1];
        service.qrNum(data).enqueue(new Callback<QRResponse>() {
            @Override
            public void onResponse(Call<QRResponse> call, Response<QRResponse> response) {
                QRResponse result = response.body();

                Log.i("qr?????? num ??? ?????????(message)", result.getMessage());
                Log.i("qr?????? num ??? ?????????(qr_num)", result.getQRNum());

                qr_num[0] = Integer.parseInt(result.getQRNum());
                AppManager.getInstance().getTourList().get(i).setTodayNumber(qr_num[0]);
                Log.i("qr?????? (getTodayNumber)", Double.toString(AppManager.getInstance().getTourList().get(i).getTodayNumber()));
            }

            @Override
            public void onFailure(Call<QRResponse> call, Throwable t) {
                //Toast.makeText(LoginActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                Log.e("qr_num ???????????? ?????? ??????", t.getMessage());
            }
        });

    }

    protected void startLogin(LoginData data) {

        service.getUserData(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();


                Log.i("??????", "startLogin ????????? + " + result.getUserName());
                AppManager.getInstance().setUserName(result.getUserName());
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //Toast.makeText(LoginActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                Log.e("?????? ", " ????????? ?????? ??????" + t.getMessage());

            }
        });
    }

    protected void getCoronaInfoData() {


        service.getCoronaInfoData().enqueue(new Callback<modelResponse>() {
            @Override
            public void onResponse(Call<modelResponse> call, Response<modelResponse> response) {
                modelResponse result = response.body();
                Log.i("??????", "getCoronaInfoData : " + result.getCoronaNum());
                confirmedCorona = (float) Double.parseDouble(result.getCoronaNum());
            }

            @Override
            public void onFailure(Call<modelResponse> call, Throwable t) {
                Log.e("?????? ", " ????????? ?????? ??????" + t.getMessage());
            }
        });

    }

    protected void getWeatherInfoData() {
        service.getWeatherInfoData().enqueue(new Callback<modelResponse>() {
            @Override
            public void onResponse(Call<modelResponse> call, Response<modelResponse> response) {
                modelResponse result = response.body();
                Log.i("??????", "getWeatherInfoData(temp_min) : " + result.getTempMin());
                Log.i("??????", "getWeatherInfoData(temp_max) : " + result.getTempMax());
                Log.i("??????", "getWeatherInfoData(temp_avg) : " + result.getTempAvg());
                minTemp = (float) result.getTempMin();
                maxTemp = (float) result.getTempMax();
                avgTemp = (float) result.getTempAvg();

            }

            @Override
            public void onFailure(Call<modelResponse> call, Throwable t) {
                Log.e("?????? ", " ?????? ?????? ??????" + t.getMessage());
            }
        });

    }
}

