package org.androidtown.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<TourList> myDataList = null;

    public MyAdapter(ArrayList<TourList> dataList) {
        myDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.tourlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.imageView.setImageResource( myDataList.get(position).getImageResourceID());
        viewHolder.numericalValueId.setImageResource(myDataList.get(position).getImageResourceID());
        viewHolder.title.setText(myDataList.get(position).getTourTitle());
        viewHolder.description.setText(myDataList.get(position).getTourDescription());
        viewHolder.numericalValue.setText(myDataList.get(position).getNumericalValue());
        viewHolder.distance.setText(myDataList.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        // Adapter가 관리하는 전체 데이터 개수 반환
        return myDataList.size();
    }
}
