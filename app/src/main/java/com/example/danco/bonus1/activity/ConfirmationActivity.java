package com.example.danco.bonus1.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.danco.bonus1.R;

public class ConfirmationActivity extends ActionBarActivity {

    private static final String EXTRA_NAME = ConfirmationActivity.class.getSimpleName() + ".name";
    private static final String EXTRA_DESCRIPTION = ConfirmationActivity.class.getSimpleName() + ".description";
    private static final String EXTRA_FAVORITE = ConfirmationActivity.class.getSimpleName() + ".favorite";


    public static Intent buildIntent(Context context, String name, String desc, boolean isFav) {
        Intent intent = new Intent(context, ConfirmationActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DESCRIPTION, desc);
        intent.putExtra(EXTRA_FAVORITE, isFav);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        if (savedInstanceState == null) {

        }
    }


}
