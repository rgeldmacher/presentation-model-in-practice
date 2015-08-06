package com.rgeldmacher.presentationmodelkata;

import com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.MainActivityController;

import org.junit.Test;

import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_FAVORITE_COLOR;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW;
import static com.rgeldmacher.presentationmodelkata.MainActivityPresentationModel.PROPERTY_USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author robertgeldmacher
 */
public class MainActivityPresentationModelTest {

    @Test
    public void testOnSyncButtonClicked() {
        // prepare
        MainActivityController activityController = mock(MainActivityController.class);
        MainActivityPresentationModel presentationModel = new MainActivityPresentationModel();
        presentationModel.setMainActivityController(activityController);

        // run
        presentationModel.onSyncButtonClicked();

        // verify
        verify(activityController, times(1)).syncData();
    }

    @Test
    public void testOnStartActivityButtonClicked() {
        // prepare
        MainActivityController activityController = mock(MainActivityController.class);
        MainActivityPresentationModel presentationModel = new MainActivityPresentationModel();
        presentationModel.setMainActivityController(activityController);

        // run
        presentationModel.onShowOtherScreenButtonClicked();

        // verify
        verify(activityController, times(1)).showOtherScreen();
    }

    @Test
    public void testSetUser() {
        // prepare
        OnPropertyChangedListener propertyChangedListener = mock(OnPropertyChangedListener.class);
        MainActivityPresentationModel presentationModel = new MainActivityPresentationModel();
        presentationModel.setMainActivityController(mock(MainActivityController.class));
        presentationModel.setPropertyChangedListener(propertyChangedListener);
        User user = new User();
        user.setUserName("Hans Test");
        user.setFavoriteColor(R.color.red);

        // run
        presentationModel.setUser(user);

        // verify
        assertEquals("Hans Test", presentationModel.getUserName());
        assertEquals(R.color.red, presentationModel.getFavoriteColor());
        assertEquals(true, presentationModel.showFavoriteColorView());
        assertEquals(false, presentationModel.showNoFavoriteColorView());
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_USER_NAME));
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_FAVORITE_COLOR));
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_SHOW_FAVORITE_COLOR_VIEW));
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW));
    }

    @Test
    public void testSetUserNoFavoriteColor() {
        // prepare
        OnPropertyChangedListener propertyChangedListener = mock(OnPropertyChangedListener.class);
        MainActivityPresentationModel presentationModel = new MainActivityPresentationModel();
        presentationModel.setMainActivityController(mock(MainActivityController.class));
        presentationModel.setPropertyChangedListener(propertyChangedListener);
        User user = new User();
        user.setUserName("Hans Test");

        // run
        presentationModel.setUser(user);

        // verify
        assertEquals("Hans Test", presentationModel.getUserName());
        assertEquals(R.color.transparent, presentationModel.getFavoriteColor());
        assertEquals(false, presentationModel.showFavoriteColorView());
        assertEquals(true, presentationModel.showNoFavoriteColorView());
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_USER_NAME));
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_FAVORITE_COLOR));
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_SHOW_FAVORITE_COLOR_VIEW));
        verify(propertyChangedListener, times(1)).onPropertyChanged(eq(PROPERTY_SHOW_NO_FAVORITE_COLOR_VIEW));
    }
}
