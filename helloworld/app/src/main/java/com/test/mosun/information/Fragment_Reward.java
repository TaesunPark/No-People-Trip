package com.test.mosun.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.mosun.MainActivity;
import com.test.mosun.R;


public class Fragment_Reward extends Fragment {
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

        //버튼 클릭 이벤트 생성
        Button total_stamp_button = view.findViewById(R.id.total_stamp_button);
        total_stamp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 오류오류 grid layout!!!
//                ((MainActivity)getActivity()).replaceFragment(Fragment_MyStamp.newInstance());

//                Intent intent = new Intent(getActivity(),Activity_myPage.class);
////                Log.d("@@", "?");
//                startActivity(intent);

//                ((Activity_myPage)getActivity()).findViewById(R.id.my_page);
//                getActivity().startActivity(new Intent(getActivity(), Activity_myPage.class));
//
//                Intent intent = new Intent(getActivity(),Activity_myPage.class);
//                startActivity(intent);
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

