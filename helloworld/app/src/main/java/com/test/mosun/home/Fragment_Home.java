package com.test.mosun.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.test.mosun.MainActivity;
import com.test.mosun.R;
import com.test.mosun.activity.CoursePopupActivity;
import com.test.mosun.camera.CameraActivity;

import java.lang.reflect.Field;
import java.util.List;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Fragment_Home extends Fragment {

    private static final long MIN_CLICK_INTERVAL = 600;
    private long mLastClickTime;

    List<areaItem> list;
    areaItem item;

    public Fragment_Home() {
    }

    public static Fragment_Home newInstance() {
        Fragment_Home fragment = new Fragment_Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("home","home");

//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        SliderView sliderView = view.findViewById(R.id.imageSlider);
//        SliderAdapter sliderAdapter = new SliderAdapter(getContext());
//        sliderView.setSliderAdapter(sliderAdapter);
//
//        sliderView.setIndicatorAnimation(IndicatorAnimations.COLOR);
//        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        sliderView.startAutoCycle();
//
//        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
//            @Override
//            public void onIndicatorClicked(int position) {
//                sliderView.setCurrentPagePosition(position);
//            }
//        });

        View view = inflater.inflate(R.layout.fragment_second_home, container, false);

        ImageView maskStampImageView = (ImageView)view.findViewById(R.id.home_mask_stamp);
        ImageView untactStampImageView = (ImageView)view.findViewById(R.id.home_untact_stamp);
        maskStampImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CameraActivity.class);
                startActivity(intent);
            }
        });
        untactStampImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), CoursePopupActivity.class);
//                startActivity(intent);

                //backspace처리해줘야함...!!
                // getActivity()로 MainActivity의 replaceFragment를 불러옵니다.
                ((MainActivity)getActivity()).replaceFragment(Fragment_selectArea.newInstance());    // 새로 불러올 Fragment의 Instance를 Main으로 전달


            }
        });




        return view;
    }
}


class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {
    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load("https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=33b92dda-ba19-4a54-8b7c-4af34df8abd1&mode=raw")
                        .centerCrop()
                        .into(viewHolder.imageView);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.home_2)
                        .centerCrop()
                        .into(viewHolder.imageView);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.home_3)
                        .centerCrop()
                        .into(viewHolder.imageView);
                break;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_home_background);
            this.itemView = itemView;
        }
    }
}