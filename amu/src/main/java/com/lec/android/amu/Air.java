package com.lec.android.amu;

import java.io.Serializable;

public class Air implements Serializable {

    int photo;
    String dust; //미세먼지
    String fineDust; //초미세먼지

    public Air() {
    }

    public Air(int photo, String dust, String fineDust) {
        this.photo = photo;
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
}//end class
