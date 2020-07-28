package com.test.mosun.stamp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.mosun.AppManager;
import com.test.mosun.MainActivity;
import com.test.mosun.R;

import java.util.ArrayList;


public class Fragment_Stamp extends Fragment {

    private static final long MIN_CLICK_INTERVAL=600;
    private long mLastClickTime;
    MainActivity mainActivity;
    Spinner spinner;
    StampExpandableGridView stampGridView;
    StampAdapter stampAdapter;
    ArrayList<TourList> tourList, searchList;
    //ProgresssBar progresssBar;
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
        tourList = AppManager.getInstance().getTourLsit();
        searchList = new ArrayList<>();
        searchList.addAll(tourList);
        mainActivity = (MainActivity) getActivity();
        searchword = "";
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
//        spinner.setOnItemSelectedListener(this);
        Log.d("onCreateView", "stamp 갱신");

        stampGridView = view.findViewById(R.id.gridview_stamp);
        stampAdapter = new StampAdapter(getContext(),
                R.layout.item_stamp, searchList);
        stampGridView.setAdapter(stampAdapter); // 어댑터를 그리드 뷰에 적용
        stampGridView.setOnItemClickListener(itemClickListener);

//        final EditText editText = view.findViewById(R.id.search_edit_text);
//        editText.addTextChangedListener(textWatcher);

//        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i == EditorInfo.IME_ACTION_SEARCH)
//                    inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//                return false;
//            }
//        });

//        ImageButton helpButton = view.findViewById(R.id.help_btn);
//        helpButton.setOnClickListener(helpClickListener);

        ScrollView scrollView = view.findViewById(R.id.scrollview);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                // [Y축] i1: before i3: after
//                if (i1 <= 0 || i3 - i1 > 0) {
//                    // top & scroll up
//                    mainActivity.changeCurveBottomBarVisibility(true);
//                } else if (i3 - i1 < 0) {
//                    // scroll down
//                    mainActivity.changeCurveBottomBarVisibility(false);
//                }

            }
        });

//        ImageButton achievementsButton = view.findViewById(R.id.achievements_button);
//        achievementsButton.setOnClickListener(achivementsClickListener);
//        if (AppManager.getInstance().collectionCount >= 0 && AppManager.getInstance().collectionCount <= 121 / 5) {
//            achievementsButton.setImageResource(R.drawable.level_1);
//        } else if (AppManager.getInstance().collectionCount <= 121 / 5 * 2) {
//            achievementsButton.setImageResource(R.drawable.level_2);
//        } else if (AppManager.getInstance().collectionCount <= 121 / 5 * 3) {
//            achievementsButton.setImageResource(R.drawable.level_3);
//        } else if (AppManager.getInstance().collectionCount <= 121 / 5 * 4) {
//            achievementsButton.setImageResource(R.drawable.level_4);
//        } else if (AppManager.getInstance().collectionCount <= 121) {
//            achievementsButton.setImageResource(R.drawable.level_5);
//        }

//        progressBar = view.findViewById(R.id.progressBar);
//        progressBar.setProgress(AppManager.getInstance().collectionCount);

        return view;
    }
    private GridView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            long currentClickTime= SystemClock.uptimeMillis();
            long elapsedTime=currentClickTime-mLastClickTime;
            mLastClickTime=currentClickTime;

            // 중복 클릭인 경우
            if(elapsedTime<=MIN_CLICK_INTERVAL){
                return;
            }

            // 아이템 클릭시 상세 페이지로 넘어감
            Intent intent = new Intent(getContext(), DetailPopUpActivity.class);
            //intent.putExtra(SELECTED_ITEM_KEY, searchList.get(i));
            startActivity(intent);
        }
    };
}

