package com.rgeldmacher.presentationmodelkata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        user.setUserName("Hans Test");
        user.setFavoriteColor(R.color.red);

        initViews();
    }

    private void initViews() {
        ((TextView) findViewById(R.id.userName)).setText(user.getUserName());
        View favColorView = findViewById(R.id.favColor);
        View noFavColorView = findViewById(R.id.noFavColor);

        int colorRes = user.getFavoriteColor();
        if (colorRes != -1) {
            noFavColorView.setVisibility(View.GONE);
            favColorView.setBackgroundColor(getResources().getColor(colorRes));
            favColorView.setVisibility(View.VISIBLE);
        } else {
            favColorView.setVisibility(View.GONE);
            noFavColorView.setVisibility(View.VISIBLE);
        }
    }
}
