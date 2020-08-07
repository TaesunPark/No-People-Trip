package com.test.mosun.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.information.my_stamp.MyStampGridView;


public class Fragment_Reward extends Fragment {
    ProgressBar progressBar;

    public Fragment_Reward(){

    }

    public static Fragment_Reward newInstance() {
        Fragment_Reward fragment = new Fragment_Reward();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_reward, container, false);

        //프로그래스바 채우기
        progressBar = view.findViewById(R.id.total_progress_bar);
        progressBar.setProgress(AppManager.getInstance().stampCount);//관광지에 따른 스탬프 카운트로 바꾸기

        //버튼 클릭 이벤트 생성
        Button total_stamp_button = view.findViewById(R.id.total_stamp_button);
        total_stamp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(Fragment_MyStamp.newInstance());
            }
        });

        Button information_button = view.findViewById(R.id.information_button);
        information_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(Fragment_MyInformation.newInstance());
            }
        });

        Button logout_button = view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        Button withdraw_button = view.findViewById(R.id.withdraw_button);
        withdraw_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
