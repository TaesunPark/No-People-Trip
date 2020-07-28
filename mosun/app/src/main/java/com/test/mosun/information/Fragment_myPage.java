package com.test.mosun.information;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class Fragment_myPage extends Fragment {
    public Fragment_myPage(){

    }

    public static Fragment_myPage newInstance() {
        Fragment_myPage fragment = new Fragment_myPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }
}
