package org.androidtown.myapplication;

import android.media.Image;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderTourList extends RecyclerView.ViewHolder {
    ImageView numericalValueId;
    TextView title;
    TextView description;
    TextView distance;
    TextView numericalValue;
//    TextView numericalValueID;

    public ViewHolderTourList(@NonNull View itemView) {
        super(itemView);
        numericalValueId = itemView.findViewById(R.id.numericalValueImage);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        distance = itemView.findViewById(R.id.distance);
        numericalValue = itemView.findViewById(R.id.numericalValue);

    }

    public void onBind(TourList data){
        title.setText(data.getTourTitle());
        description.setText(data.getTourDescription());
        distance.setText(data.getDistance());
        // 이미지 2개 코드 미작성
    }
}
