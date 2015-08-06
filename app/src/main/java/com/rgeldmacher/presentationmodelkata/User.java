package com.rgeldmacher.presentationmodelkata;

import android.support.annotation.ColorRes;

/**
 * @author robertgeldmacher
 */
public class User {

    private String userName;
    private int favoriteColor = -1;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ColorRes
    public int getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(@ColorRes int favoriteColor) {
        this.favoriteColor = favoriteColor;
    }
}
