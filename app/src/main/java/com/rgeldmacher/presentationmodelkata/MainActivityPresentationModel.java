package com.rgeldmacher.presentationmodelkata;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author robertgeldmacher
 */
public class MainActivityPresentationModel {

    public interface MainActivityController {

        void syncData();

        void showOtherScreen();
    }

    public static final String PROPERTY_USER_NAME = "userName";
    public static final String PROPERTY_FAVORITE_COLOR = "favoriteColor";
    public static final String PROPERTY_SHOW_FAVORITE_COLOR_VIEW = "showFavoriteColorView";
    public static final String PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW = "showNoFavoriteColorView";

    private MainActivityController controller;

    @Nullable
    private User user;

    @Nullable
    private OnPropertyChangedListener propertyChangedListener;

    public MainActivityPresentationModel(MainActivityController controller) {
        this.controller = controller;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
        notifyPropertyChanged(PROPERTY_USER_NAME);
        notifyPropertyChanged(PROPERTY_FAVORITE_COLOR);
        notifyPropertyChanged(PROPERTY_SHOW_FAVORITE_COLOR_VIEW);
        notifyPropertyChanged(PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW);
    }

    public void onSyncButtonClicked() {
        controller.syncData();
    }

    public void onShowOtherScreenButtonClicked() {
        controller.showOtherScreen();
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

    public void setPropertyChangedListener(@Nullable OnPropertyChangedListener propertyChangedListener) {
        this.propertyChangedListener = propertyChangedListener;
    }

    private void notifyPropertyChanged(String property) {
        if (propertyChangedListener != null) {
            propertyChangedListener.onPropertyChanged(property);
        }
    }

}
