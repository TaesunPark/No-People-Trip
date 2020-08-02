package com.test.mosun.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.information.my_stamp.MyStampAdapter;
import com.test.mosun.information.my_stamp.MyStampGridView;
import com.test.mosun.stamp.TourList;

import java.util.ArrayList;

public class Fragment_MyStamp extends Fragment {

    ArrayList<TourList> tourList;
    MyStampGridView myStampGridView;
    MyStampAdapter myStampAdapter;

    public Fragment_MyStamp(){

    }

    public static Fragment_MyStamp newInstance() {
        Fragment_MyStamp fragment = new Fragment_MyStamp();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tourList = AppManager.getInstance().getTourList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stamp, container, false);

        Button back_button = view.findViewById(R.id.back_button2);
        back_button.setOnClickListener(new View.OnClickListener() {  //뒤로가기 버튼 누르면 마이페이지(이전 페이지)로 돌아간다.

            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(Fragment_Reward.newInstance());
            }
        });

//        myStampGridView = view.findViewById(R.id.gridview_my_stamp);
//        myStampAdapter = new MyStampAdapter(getContext(), R.layout.item_my_stamp, tourList);
//        myStampGridView.setAdapter(myStampAdapter);

        return view;
    }
}
