package com.example.danco.bonus1;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String TAG = ApplicationTest.class.getSimpleName() + ".tag";

    public ApplicationTest() {
        super(Application.class);
    }

    public void testShowGridView() {
        Log.i(TAG, "In testShowGridView method");
    }
}