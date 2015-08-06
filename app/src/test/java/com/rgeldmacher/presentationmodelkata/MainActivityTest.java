package com.rgeldmacher.presentationmodelkata;

import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author robertgeldmacher
 */
public class MainActivityTest {

    @Test
    public void testOnCreate() {
        MainActivity activity = new MainActivity();
        activity.onCreate(null);

        assertEquals("Hans Test", ((TextView) activity.findViewById(R.id.userName)).getText());
    }
}
