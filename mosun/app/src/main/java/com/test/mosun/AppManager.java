package com.test.mosun;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AppManager {
    private static AppManager instance = null;
    private List<areaItem> list;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }
    public List<areaItem> getAreaList() {
        return list;
    }
    public void setAreaList(List<areaItem> list) {
        this.list = list;
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
}
