package com.test.mosun.stamp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.test.mosun.R;

import java.lang.reflect.Field;
import java.util.List;

//import jp.wasabeef.glide.transformations.ColorFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import com.test.mosun.R;

public class
StampAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<TourList> list;  // item 목록
    LayoutInflater layoutInflater;

    TourList item;     // item 정보 객체

    public StampAdapter(Context context, int layout, List<TourList> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

    }

    public void updateAdpater(List<TourList> list) {
        this.list = list;
        this.notifyDataSetChanged();
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
        TextView textView = view.findViewById(R.id.landmark_name);

        try {
            Field field = R.drawable.class.getField(  Integer.toString(item.getImageNumericalValueID()));
            int drawableID = field.getInt(null);

            textView.setTextColor(view.getResources().getColor(R.color.colorBlack));
            MultiTransformation multi = new MultiTransformation(
                    new CircleCrop()
            );
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.border_normal));
            Glide.with(view)
                    .load(drawableID)
                    .apply(bitmapTransform(multi))
                    .thumbnail(0.1f).into(imageView);

//            if (item.isCollected()) {
//                textView.setTextColor(view.getResources().getColor(R.color.colorNormalText));
//                MultiTransformation multi = new MultiTransformation(
//                        new CircleCrop()
//                );
//                view.setBackground(ContextCompat.getDrawable(context, R.drawable.border_active));
//                Glide.with(view)
//                        .load(drawableID)
//                        .apply(bitmapTransform(multi))
//                        .thumbnail(0.1f).into(imageView);
//            } else {
//                textView.setTextColor(view.getResources().getColor(R.color.no));
//                MultiTransformation multi = new MultiTransformation(
//                        new CircleCrop(),
//                        new ColorFilterTransformation(
//                                Color.argb(200, 210, 210, 210))
//                );
//                view.setBackground(ContextCompat.getDrawable(context, R.drawable.border_normal));
//                Glide.with(view)
//                        .load(drawableID)
//                        .apply(bitmapTransform(multi))
//                        .thumbnail(0.1f).into(imageView);
//            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        TextView landmarkNameView = view.findViewById(R.id.landmark_name);
        landmarkNameView.setText(item.getTourTitle());

        //gps,predit_image

        return view;
    }

}