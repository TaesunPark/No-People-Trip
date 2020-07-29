package org.androidtown.myapplication;

import android.media.Image;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderStamp extends RecyclerView.ViewHolder {
    ImageView numericalValueId;
    TextView title;
//    TextView numericalValueID;

    public ViewHolderStamp(@NonNull View itemView) {
        super(itemView);
        numericalValueId = itemView.findViewById(R.id.numericalValueImage);
        title = itemView.findViewById(R.id.stampDescription);
    }

    public void onBind(Stamp data){
        title.setText(data.getTourDescription());
        // 이미지 2개 코드 미작성
    }
}
