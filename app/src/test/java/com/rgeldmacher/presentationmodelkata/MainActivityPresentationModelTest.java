package com.rgeldmacher.presentationmodelkata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author robertgeldmacher
 */
public class MainActivityPresentationModelTest {

    @Test
    public void testSetUser() {
        // prepare
        MainActivityPresentationModel presentationModel = new MainActivityPresentationModel();
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
    }

    @Test
    public void testSetUserNoFavoriteColor() {
        // prepare
        MainActivityPresentationModel presentationModel = new MainActivityPresentationModel();
        User user = new User();
        user.setUserName("Hans Test");

        // run
        presentationModel.setUser(user);

        // verify
        assertEquals("Hans Test", presentationModel.getUserName());
        assertEquals(R.color.transparent, presentationModel.getFavoriteColor());
        assertEquals(false, presentationModel.showFavoriteColorView());
        assertEquals(true, presentationModel.showNoFavoriteColorView());
    }
}
