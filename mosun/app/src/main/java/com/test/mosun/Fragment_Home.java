package com.test.mosun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;

import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.test.mosun.login.LoginActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

        list = AppManager.getInstance().getAreaList();
        GridView gridView = view.findViewById(R.id.gridview_select_area);
        gridView.setVisibility(View.VISIBLE);
        AreaAdapter areaAdapter = new AreaAdapter(getContext(),
                R.layout.item_area_list, list);
        gridView.setAdapter(areaAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                long currentClickTime = SystemClock.uptimeMillis();
                long elapsedTime = currentClickTime - mLastClickTime;
                mLastClickTime = currentClickTime;

                // 중복 클릭인 경우
                if (elapsedTime <= MIN_CLICK_INTERVAL) {
                    return;
                }


                //코스선택 or 추천 코스
                Intent intent = new Intent(getContext(), LoginActivity.class);
                //intent.putExtra(Fragment_Plant_Book.SELECTED_ITEM_KEY, plantsToday.get(i));
                startActivity(intent);
            }
        });

        return view;
    }
}

class AreaAdapter extends BaseAdapter {
    private Context context;
    int layout;
    List<areaItem> list;
    areaItem  item;
    LayoutInflater layoutInflater;

    public AreaAdapter(Context context, int layout, List<areaItem> list) {
        this.list = list;
        this.layout = layout;
        this.context = context;

        layoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = layoutInflater.inflate(layout, null);


        item = list.get(i);
        ImageView imageView = view.findViewById(R.id.image_thumb);
        TextView textView = view.findViewById(R.id.name);
        textView.setText(item.getName());

        try {
            Field field = R.drawable.class.getField("area_" + item.getId());
            int drawableID = field.getInt(null);

            //view.setBackground(ContextCompat.getDrawable(context, R.drawable.border_oval_active));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.setMargins(5, 5, 5, 5);
            //imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_lotus_active));
            textView.setTextColor(view.getResources().getColor(R.color.colorBase));


            MultiTransformation multi = new MultiTransformation(
                    new CircleCrop()
            );

            Glide.with(view)
                    .load(drawableID)
                    .apply(bitmapTransform(multi))
                    .thumbnail(0.1f).into(imageView);
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


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