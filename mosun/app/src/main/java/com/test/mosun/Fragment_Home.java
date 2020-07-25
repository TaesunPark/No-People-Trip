package com.test.mosun;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;

import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Fragment_Home extends Fragment {

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

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SliderView sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(getContext());
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.COLOR);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
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