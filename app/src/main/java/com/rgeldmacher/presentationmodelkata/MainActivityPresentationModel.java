package com.rgeldmacher.presentationmodelkata;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author robertgeldmacher
 */
public class MainActivityPresentationModel {

    @Nullable
    private User user;

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @NonNull
    public String getUserName() {
        return user != null && user.getUserName() != null ? user.getUserName() : "";
    }

    @ColorRes
    public int getFavoriteColor() {
        return user != null && user.getFavoriteColor() != -1 ? user.getFavoriteColor() : R.color.transparent;
    }

    public boolean showFavoriteColorView() {
        return user != null && user.getFavoriteColor() != -1;
    }

    public boolean showNoFavoriteColorView() {
        return user == null || user.getFavoriteColor() == -1;
    }
}
