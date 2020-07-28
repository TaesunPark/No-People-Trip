package com.test.mosun;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.mosun.qrcode.QRPopupActivity;
import com.test.mosun.stamp.TourList;

import java.util.ArrayList;
import java.util.List;

public class AppManager {
    private static AppManager instance = null;
    private List<areaItem> arealist;
    private ArrayList<TourList> tourList;
    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }
    public List<areaItem> getAreaList() {
        return arealist;
    }
    public void setAreaList(List<areaItem> arealist) {
        this.arealist = arealist;
    }

    public ArrayList<TourList> getTourLsit() {
        return tourList;
    }
    public void setTourList(ArrayList<TourList> tourList) {
        this.tourList = tourList;
    }

    private String userId = null;
    public void setUserID(String userID) {this.userId = userID;}
    public String getUserId() { return userId;}

    private FloatingActionButton floatingActionButton;
    public void setMenuFloatingActionButton (FloatingActionButton floatingActionButton) {this.floatingActionButton = floatingActionButton;}
    public FloatingActionButton getMenuFloatingActionButton() { return floatingActionButton; }

    private MainActivity mainActivity;
    public void setMainActivity(MainActivity mainActivity) {this.mainActivity = mainActivity; }
    public MainActivity getMainActivity(){ return  mainActivity; }

    private QRPopupActivity qrPopUpActivity;
    public void setQRPopUpActivity (QRPopupActivity qrPopUpActivity) {this.qrPopUpActivity = qrPopUpActivity; }
    public QRPopupActivity getQRPopUpActivity() { return  qrPopUpActivity; }

}
