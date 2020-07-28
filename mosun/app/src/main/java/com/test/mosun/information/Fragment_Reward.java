package com.test.mosun.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.test.mosun.R;

import net.daum.mf.map.api.MapView;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reward, container, false);


        return view;
    }
}

