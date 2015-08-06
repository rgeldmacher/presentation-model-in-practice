package com.rgeldmacher.presentationmodelkata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private User user;
    private MainActivityPresentationModel presentationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        user.setUserName("Hans Test");
        user.setFavoriteColor(R.color.red);
        presentationModel = new MainActivityPresentationModel();
        presentationModel.setUser(user);

        initViews();
    }

    private void initViews() {
        ((TextView) findViewById(R.id.userName)).setText(presentationModel.getUserName());
        findViewById(R.id.favColor).setBackgroundColor(getResources().getColor(presentationModel.getFavoriteColor()));
        setViewVisibility(findViewById(R.id.favColor), presentationModel.showFavoriteColorView());
        setViewVisibility(findViewById(R.id.noFavColor), presentationModel.showNoFavoriteColorView());
    }

    private void setViewVisibility(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
