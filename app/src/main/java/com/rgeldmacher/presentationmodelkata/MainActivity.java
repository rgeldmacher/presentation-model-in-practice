package com.rgeldmacher.presentationmodelkata;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_FAVORITE_COLOR;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_USER_NAME;

public class MainActivity extends AppCompatActivity implements OnPropertyChangedListener, MainActivityPresentationModel.MainActivityController {

    private MainActivityPresentationModel presentationModel;
    private User user;

    private TextView userNameView;
    private View favoriteColorView;
    private View noFavoriteColorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        user = new User();
        user.setUserName("Hans Test");
        user.setFavoriteColor(R.color.red);

        if (savedInstanceState == null) {
            presentationModel = new MainActivityPresentationModel();
            presentationModel.setUser(user);
            presentationModel.setMainActivityController(this);
            presentationModel.setPropertyChangedListener(this);
        } else {
            restoreData(this);
            presentationModel.setMainActivityController(this);
            presentationModel.setPropertyChangedListener(this);
        }

        bindData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presentationModel.setMainActivityController(null);
        presentationModel.setPropertyChangedListener(null);
        retainData(this);
        super.onSaveInstanceState(outState);
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

    private void bindData() {
        userNameView.setText(presentationModel.getUserName());
        favoriteColorView.setBackgroundColor(getResources().getColor(presentationModel.getFavoriteColor()));
        setViewVisibility(favoriteColorView, presentationModel.showFavoriteColorView());
        setViewVisibility(noFavoriteColorView, presentationModel.showNoFavoriteColorView());
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
                    Thread.sleep(1000);
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

    private static void retainData(MainActivity activity) {
        MainActivityRetainedDataFragment retainedFragment = getRetainedFragment(activity);
        if (retainedFragment != null) {
            retainedFragment.retainedPresentationModel = activity.presentationModel;
        }
    }

    private static void restoreData(MainActivity activity) {
        MainActivityRetainedDataFragment retainedFragment = getRetainedFragment(activity);
        if (retainedFragment != null) {
            if (retainedFragment.retainedPresentationModel != null) {
                activity.presentationModel = retainedFragment.retainedPresentationModel;
            }
        }
    }

    private static MainActivityRetainedDataFragment getRetainedFragment(FragmentActivity activity) {
        if (activity != null && activity.getSupportFragmentManager() != null) {
            FragmentManager fm = activity.getSupportFragmentManager();
            Fragment retainedFragment = fm.findFragmentByTag(MainActivityRetainedDataFragment.FRAGMENT_TAG);
            if (retainedFragment == null) {
                retainedFragment = new MainActivityRetainedDataFragment();
                fm.beginTransaction().add(retainedFragment, MainActivityRetainedDataFragment.FRAGMENT_TAG).commit();
            }
            if (retainedFragment instanceof MainActivityRetainedDataFragment) {
                return (MainActivityRetainedDataFragment) retainedFragment;
            }
        }
        return null;
    }

    /**
     * Retained fragment to store objects during orientation change.
     */
    public static class MainActivityRetainedDataFragment extends Fragment {

        private static final String FRAGMENT_TAG = "SideActivityRetainedDataFragment";
        MainActivityPresentationModel retainedPresentationModel;

        public MainActivityRetainedDataFragment() {
            setRetainInstance(true);
        }
    }
}
