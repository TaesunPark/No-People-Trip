package com.test.mosun.stamp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.information.Fragment_Reward;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class Fragment_Stamp extends Fragment {

    private static final long MIN_CLICK_INTERVAL=600;
    private long mLastClickTime;
    MainActivity mainActivity;
    Spinner spinner;
    StampExpandableGridView stampGridView;
    StampAdapter stampAdapter;
    ArrayList<TourList> tourList, searchList;
    ProgressBar progressBar;
    String searchword;


    public Fragment_Stamp(){

    }

    public static Fragment_Stamp newInstance() {
        Fragment_Stamp fragment = new Fragment_Stamp();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tourList = AppManager.getInstance().getTourList();
        searchList = new ArrayList<>();
        searchList.addAll(tourList);
        mainActivity = (MainActivity) getActivity();
        searchword = "";

        //서울 값 받기

        Bundle bundle = getArguments();
        if(bundle.getString("area") != null){
            String area = bundle.getString("area"); //Name 받기.
            Log.i("bundle",area);

        }
        else
        {
            Toast.makeText(getContext(),"홈화면의 지역선택부터 해주세요",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stamp, container, false);



        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sort_type, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Fragment_Reward fragment_Reward = null;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                if (position == 1) {
                    Log.d("박태순", String.valueOf(position));
                    tourList = AppManager.getInstance().getTourList();
                    searchList = new ArrayList<>();
                    searchList.addAll(tourList);
                    mainActivity = (MainActivity) getActivity();
                    searchword = "";
                    fragment_Reward = fragment_Reward.newInstance();
                    fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Reward, "reward").commit();
                } else if (position == 2) {
                    Log.d("박태순", String.valueOf(position));
                    tourList = AppManager.getInstance().getJeonjuList();
                    searchList = new ArrayList<>();
                    searchList.addAll(tourList);
                    mainActivity = (MainActivity) getActivity();
                    searchword = "";
                    fragment_Reward = fragment_Reward.newInstance();
                    fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Reward, "reward").commit();
                } else if (position == 3) {
                    Log.d("박태순", String.valueOf(position));
                    tourList = AppManager.getInstance().getSunchangList();
                    searchList = new ArrayList<>();
                    searchList.addAll(tourList);
                    mainActivity = (MainActivity) getActivity();
                    searchword = "";
                    fragment_Reward = fragment_Reward.newInstance();
                    fragmentManager.beginTransaction().add(R.id.frame_container, fragment_Reward, "reward").commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("onCreateView", "stamp 갱신");

        stampGridView = view.findViewById(R.id.gridview_stamp);
        stampAdapter = new StampAdapter(getContext(),
                R.layout.item_stamp, searchList);
        stampGridView.setAdapter(stampAdapter); // 어댑터를 그리드 뷰에 적용
        stampGridView.setOnItemClickListener(itemClickListener);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setProgress(AppManager.getInstance().stampCount);//관광지에 따른 스탬프 카운트로 바꾸기


        return view;
    }



    private GridView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object vo = (Object)adapterView.getAdapter().getItem(i);
            String tourTitle = null;
            String predictionNumber = null;
            String todayNumber = null;
            String distance = null;

            for (Field field : vo.getClass().getDeclaredFields()) {

                field.setAccessible(true);
                Object value = null; // 필드에 해당하는 값을 가져옵니다.
                try {
                    value = field.get(vo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if(String.valueOf(field.getName()).equals("tourTitle"))
                {
                    tourTitle = String.valueOf(value);
                }
                if(String.valueOf(field.getName()).equals("todayNumber")){
                    todayNumber = String.valueOf(value);
                }
                if(String.valueOf(field.getName()).equals("pridictionNumber")){
                    predictionNumber = String.valueOf(value);
                }
                if(String.valueOf(field.getName()).equals("distance")){
                    distance = String.valueOf(value);
                }
            }

            long currentClickTime= SystemClock.uptimeMillis();
            long elapsedTime=currentClickTime-mLastClickTime;
            mLastClickTime=currentClickTime;

            Log.d("박태순", String.valueOf(distance));


            // 중복 클릭인 경우
            if(elapsedTime<=MIN_CLICK_INTERVAL){
                return;
            }

            // 아이템 클릭시 상세 페이지로 넘어감
            Intent intent = new Intent(getContext(), DetailPopUpActivity.class);
            intent.putExtra("key", tourTitle);
            intent.putExtra("todayNumber", todayNumber);
            intent.putExtra("predictionNumber", predictionNumber);
            intent.putExtra("distance", distance);

            startActivity(intent);
        }
    };

}

