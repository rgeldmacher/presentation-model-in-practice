package com.rgeldmacher.presentationmodelkata;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_FAVORITE_COLOR;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_USER_NAME;

public class MainActivity extends AppCompatActivity implements OnPropertyChangedListener, MainActivityPresentationModel.MainActivityController {

    private MainActivityPresentationModel presentationModel;

    private TextView userNameView;
    private View favoriteColorView;
    private View noFavoriteColorView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        user = new User();
        user.setUserName("Hans Test");
        user.setFavoriteColor(R.color.red);

        presentationModel = new MainActivityPresentationModel(this);
        presentationModel.setPropertyChangedListener(this);
        presentationModel.setUser(user);
    }

    private void bindViews() {
        userNameView = (TextView) findViewById(R.id.userName);
        favoriteColorView = findViewById(R.id.favColor);
        noFavoriteColorView = findViewById(R.id.noFavColor);

        findViewById(R.id.syncButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentationModel.onSyncButtonClicked();
            }
        });

        findViewById(R.id.startActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentationModel.onShowOtherScreenButtonClicked();
            }
        });
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

    @Override
    public void syncData() {
        new AsyncTask<Void, Void, User>() {

            @Override
            protected User doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                User syncUser = new User();
                syncUser.setUserName("Hans Test");
                if (user.getFavoriteColor() == R.color.red) {
                    syncUser.setFavoriteColor(R.color.blue);
                } else {
                    syncUser.setFavoriteColor(R.color.red);
                }

                return syncUser;
            }

            @Override
            protected void onPostExecute(User newUser) {
                user = newUser;
                presentationModel.setUser(newUser);
            }
        }.execute();
    }

    @Override
    public void showOtherScreen() {
        startActivity(new Intent(this, EmptyActivity.class));
    }

    private void setViewVisibility(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
