package com.rgeldmacher.presentationmodelkata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_FAVORITE_COLOR;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_USER_NAME;

public class MainActivity extends AppCompatActivity implements OnPropertyChangedListener {

    private MainActivityPresentationModel presentationModel;

    private TextView userNameView;
    private View favoriteColorView;
    private View noFavoriteColorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        final User user = new User();
        user.setUserName("Hans Test");
        user.setFavoriteColor(R.color.red);

        presentationModel = new MainActivityPresentationModel();
        presentationModel.setPropertyChangedListener(this);
        presentationModel.setUser(user);

        findViewById(R.id.userName).postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setFavoriteColor(R.color.blue);
                presentationModel.setUser(user);
            }
        }, 2000);
    }

    private void bindViews() {
        userNameView = (TextView) findViewById(R.id.userName);
        favoriteColorView = findViewById(R.id.favColor);
        noFavoriteColorView = findViewById(R.id.noFavColor);
    }

    @Override
    public void onPropertyChanged(String property) {
        switch (property) {
            case PROPERTY_USER_NAME:
                userNameView.setText(presentationModel.getUserName());
                break;
            case PROPERTY_FAVORITE_COLOR:
                favoriteColorView.setBackgroundColor(getResources().getColor(presentationModel.getFavoriteColor()));
                break;
            case PROPERTY_SHOW_FAVORITE_COLOR_VIEW:
                setViewVisibility(favoriteColorView, presentationModel.showFavoriteColorView());
                break;
            case PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW:
                setViewVisibility(noFavoriteColorView, presentationModel.showNoFavoriteColorView());
                break;
            default:
                break;
        }
    }

    private void setViewVisibility(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
