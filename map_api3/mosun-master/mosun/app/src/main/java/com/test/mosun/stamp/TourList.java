package com.test.mosun.stamp;

import android.view.View;

public class TourList {

    private View view;
    private String tourTitle; // 여행 제목
    private String tourDescription; // 여행 설명
    private String distance; // 거리
    private String numericalValue; // 수치값
    private int imageNumericalValueID; // 수치값에 따른 이미지 id

    // 기본 생성자 초기화
    public TourList(){

        tourTitle = "0";
        tourDescription = "0";
        distance = "0";
        numericalValue = "0";
        imageNumericalValueID = 0;
    }

    public TourList(String tourTitle, String tourDescription, String distance, String numericalValue, int imageNumericalValueID)
    {
        this.tourTitle = tourTitle;
        this.tourDescription = tourDescription;
        this.distance = distance;
        this.numericalValue = numericalValue;
        this.imageNumericalValueID = imageNumericalValueID;
    }




    public int getImageNumericalValueID() {
        return imageNumericalValueID;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String data) { tourTitle = data; }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getDistance() { return distance; }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getNumericalValue() {
        return numericalValue;
    }


    public void setNumericalValue(String numericalValue) {
        this.numericalValue = numericalValue;
    }

    public void setImageNumericalValueID(int imageNumericalValueID) {
        this.imageNumericalValueID = imageNumericalValueID;
    }

}
