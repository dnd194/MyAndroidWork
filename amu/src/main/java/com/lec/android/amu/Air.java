package com.lec.android.amu;

import java.io.Serializable;

public class Air implements Serializable {

    int photo;
    String region;
    String dust; //미세먼지
    String fineDust; //초미세먼지

    public Air() {
    }

    public Air(int photo, String region, String dust, String fineDust) {
        this.photo = photo;
        this.region = region;
        this.dust = dust;
        this.fineDust = fineDust;
    }

    public Air(String region, String dust, String fineDust) {
        this.region = region;
        this.dust = dust;
        this.fineDust = fineDust;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDust() {
        return dust;
    }

    public void setDust(String dust) {
        this.dust = dust;
    }

    public String getFineDust() {
        return fineDust;
    }

    public void setFineDust(String fineDust) {
        this.fineDust = fineDust;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}//end class
