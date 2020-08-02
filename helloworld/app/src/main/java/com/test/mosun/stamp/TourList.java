package com.test.mosun.stamp;

import android.location.Location;
import android.view.View;

public class TourList {

    private View view;

    private String tourTitle; // 여행 제목
    private String tourDescription; // 여행 설명
    private double distance; // 거리
    private double x; // 경도
    private double y; // 위도
    private float pridictionNumber; // 예상 수치값
    private float todayNumber; // 오늘 방문객 수
    private int imageNumericalValueID; // 수치값에 따른 이미지 id
    private Location locationA;
    private Location locationB;

    private boolean isCollected = false;//스탬프 여부

    // 기본 생성자 초기화
    public TourList(){

        tourTitle = "0";
        tourDescription = "0";
        distance = 0;
        x = 0;
        y = 0;
        pridictionNumber = 0;
        todayNumber = 0;
        imageNumericalValueID = 0;
    }

    public TourList(String tourTitle, String tourDescription, double x,double y, float pridictionNumber,float todayNumber, int imageNumericalValueID)
    {
        this.tourTitle = tourTitle;
        this.tourDescription = tourDescription;
        this.x = x;
        this.y = y;
        this.pridictionNumber = pridictionNumber;
        this.todayNumber = todayNumber;
        this.imageNumericalValueID = imageNumericalValueID;
        locationA = new Location("point A");
        locationA.setLatitude(35.249793); // 현재위치 x
        locationA.setLatitude(128.901906); // 현재위치 y
        locationB = new Location("point B");
        locationB.setLatitude(x);
        locationB.setLatitude(y);
        distance = locationA.distanceTo(locationB);
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public void setLocationB(){
        locationB.setLatitude(x);
        locationB.setLatitude(y);
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

    public double getDistance() { return distance; }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public float getPridictionNumber() {
        return pridictionNumber;
    }

    public float getTodayNumber() {
        return todayNumber;
    }

    public void setImageNumericalValueID(int imageNumericalValueID) {
        this.imageNumericalValueID = imageNumericalValueID;
    }



}