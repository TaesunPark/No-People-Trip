package org.androidtown.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TourList> myDataList = new ArrayList<>();
    private ArrayList<Stamp> stampsList = new ArrayList<>();
    private int state;
    static public int TOURLISTSTATE = 1;
    static public int STAMPSTATE = 2;
    public MyAdapter() {
        state = 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(state == TOURLISTSTATE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tourlist, parent, false);
            return new ViewHolderTourList(view);
        } else if(state == STAMPSTATE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stamp, parent, false);
            return new ViewHolderStamp(view);
        }
        return new ViewHolderTourList(LayoutInflater.from(parent.getContext()).inflate(R.layout.tourlist, parent, false)); // 디펄트 값
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(state == TOURLISTSTATE)
        {
            ((ViewHolderTourList)holder).onBind(myDataList.get(position));
        } else if (state == STAMPSTATE)
        {
            ((ViewHolderStamp)holder).onBind(stampsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(state == TOURLISTSTATE) {
            return myDataList.size();
        }
        else if(state == STAMPSTATE) {
            return stampsList.size();
        }

        return 0;

    }

    void addItem(TourList data){
        if (state == TOURLISTSTATE) myDataList.add(data);
    }

    void addItem(Stamp data){
        if(state == STAMPSTATE) stampsList.add(data);
    }

}
