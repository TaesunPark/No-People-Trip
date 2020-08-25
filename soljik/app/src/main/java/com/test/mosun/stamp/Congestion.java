
package com.test.mosun.stamp;

import android.util.Log;
import android.widget.Switch;

public class Congestion {

    public Congestion(){

    }

    // 혼잡도 분석
    public String congestAnalysis(String name, float predictNumber){

        if(name.equals("경복궁")){
            return congestKgoung(predictNumber);
        } else if(name.equals("덕수궁")){
            return congestDgoung(predictNumber);
        } else if(name.equals("창덕궁")){
            return congestCDgoung(predictNumber);
        } else if(name.equals("창경궁")){
            return congestCKgoung(predictNumber);
        } else if(name.equals("태릉")){
            return congestTaerung(predictNumber);
        } else if(name.equals("선릉")){
            return congestSunrung(predictNumber);
        } else if(name.equals("정릉")){
            return congestJungrung(predictNumber);
        } else if(name.equals("헌릉")){
            return congestHunrung(predictNumber);
        } else if(name.equals("의릉")){
            return congestUwirung(predictNumber);
        } else if(name.equals("영휘원")){
            return congestYoungHuiwon(predictNumber);
        } else if(name.equals("경기전")){
            return congestKyoungki(predictNumber);
        } else if(name.equals("국립전주박물관")){
            return congestJeonjuMuseum(predictNumber);
        } else if(name.equals("스파라쿠아")){
            return congestSparacua(predictNumber);
        }else if(name.equals("전주 동물관")){
            return congestJeonjuZoo(predictNumber);
        } else if(name.equals("한벽문화관")){
            return congestHanbeock(predictNumber);
        } else if(name.equals("한옥레일바이크")){
            return congestHanokRailBike(predictNumber);
        } else if(name.equals("한국도로공사수목원")){
            return congestKoreaSumokwon(predictNumber);
        } else if(name.equals("강천산")){
            return congestKangcheonMountain(predictNumber);
        } else if(name.equals("황토열매마을")){
            return congestFruitVilage(predictNumber);
        } else if(name.equals("고추장익는마을")){
            return congestPaperVilage(predictNumber);
        } else if(name.equals("장류체험관")){
            return congestJangru(predictNumber);
        } else if(name.equals("산림박물관")){
            return congestSanrimMuseum(predictNumber);
        } else if(name.equals("발효소스토굴")){
            return congestSourceTogul(predictNumber);
        }

        return "err";
    }

    public String congestKgoung(float predictNumber){
        if(predictNumber >= 6300){
            return "red";
        } else if(predictNumber >= 3150) {
            return "yellow";
        }

        return "green";
    }

    public String congestDgoung(float predictNumber){

        if(predictNumber >= 6300){
            return "red";
        } else if(predictNumber >= 3150) {
            return "yellow";
        }

        return "green";
    }

    public String congestCDgoung(float predictNumber){

        if(predictNumber >= 5509){
            return "red";
        } else if(predictNumber >= 2754) {
            return "yellow";
        }

        return "green";
    }

    public String congestCKgoung(float predictNumber){
        if(predictNumber >= 2181){
            return "red";
        } else if(predictNumber >= 1090) {
            return "yellow";
        }

        return "green";
    }

    public String congestTaerung(float predictNumber){
        if(predictNumber >= 16390){
            return "red";
        } else if(predictNumber >= 8190) {
            return "yellow";
        }

        return "green";
    }

    public String congestSunrung(float predictNumber){
        if(predictNumber >= 1988){
            return "red";
        } else if(predictNumber >= 994) {
            return "yellow";
        }

        return "green";
    }

    public String congestJungrung(float predictNumber){
        if(predictNumber >= 2995){
            return "red";
        } else if(predictNumber >= 1497) {
            return "yellow";
        }

        return "green";
    }

    public String congestHunrung(float predictNumber){
        if(predictNumber >= 11930){
            return "red";
        } else if(predictNumber >= 5965) {
            return "yellow";
        }

        return "green";
    }

    public String congestUwirung(float predictNumber){
        if(predictNumber >= 4386){
            return "red";
        } else if(predictNumber >= 2193) {
            return "yellow";
        }

        return "green";
    }

    public String congestYoungHuiwon(float predictNumber){
        if(predictNumber >= 550){
            return "red";
        } else if(predictNumber >= 275) {
            return "yellow";
        }

        return "green";
    }

    public String congestKyoungki(float predictNumber){
        if(predictNumber >= 500){
            return "red";
        } else if(predictNumber >= 250) {
            return "yellow";
        }

        return "green";
    }

    public String congestJeonjuMuseum(float predictNumber){
        if(predictNumber >= 3000){
            return "red";
        } else if(predictNumber >= 1500) {
            return "yellow";
        }

        return "green";
    }

    public String congestSparacua(float predictNumber){
        if(predictNumber >= 100){
            return "red";
        } else if(predictNumber >= 50) {
            return "yellow";
        }

        return "green";
    }

    public String congestJeonjuZoo(float predictNumber){
        if(predictNumber >= 1260){
            return "red";
        } else if(predictNumber >= 630) {
            return "yellow";
        }

        return "green";
    }

    public String congestHanbeock(float predictNumber){
        if(predictNumber >= 84){
            return "red";
        } else if(predictNumber >= 42) {
            return "yellow";
        }

        return "green";
    }

    public String congestHanokRailBike(float predictNumber){
        if(predictNumber >= 60){
            return "red";
        } else if(predictNumber >= 30) {
            return "yellow";
        }

        return "green";
    }

    public String congestKoreaSumokwon(float predictNumber){
        if(predictNumber >= 3400){
            return "red";
        } else if(predictNumber >= 1700) {
            return "yellow";
        }

        return "green";
    }

    public String congestKangcheonMountain(float predictNumber){
        if(predictNumber >= 100000){
            return "red";
        } else if(predictNumber >= 50000) {
            return "yellow";
        }

        return "green";
    }

    public String congestFruitVilage(float predictNumber){
        if(predictNumber >= 32){
            return "red";
        } else if(predictNumber >= 16) {
            return "yellow";
        }

        return "green";
    }

    public String congestPaperVilage(float predictNumber){
        if(predictNumber >= 32){
            return "red";
        } else if(predictNumber >= 16) {
            return "yellow";
        }

        return "green";
    }

    public String congestJangru(float predictNumber){
        if(predictNumber >= 84){
            return "red";
        } else if(predictNumber >= 42) {
            return "yellow";
        }

        return "green";
    }

    public String congestSanrimMuseum(float predictNumber){
        if(predictNumber >= 320){
            return "red";
        } else if(predictNumber >= 160) {
            return "yellow";
        }

        return "green";
    }

    public String congestSourceTogul(float predictNumber){
        if(predictNumber >= 100){
            return "red";
        } else if(predictNumber >= 50) {
            return "yellow";
        }

        return "green";
    }
}