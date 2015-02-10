package com.example.danco.bonus1.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.danco.bonus1.R;
import com.example.danco.bonus1.fragment.ConfirmationFragment;

public class ConfirmationActivity extends ActionBarActivity
        implements ConfirmationFragment.ConfirmationFragmentListener {

    public static final String EXTRA_NAME = ConfirmationActivity.class.getSimpleName() + ".name";
    public static final String EXTRA_DESCRIPTION = ConfirmationActivity.class.getSimpleName() + ".description";
    public static final String EXTRA_FAVORITE = ConfirmationActivity.class.getSimpleName() + ".favorite";

    private static final String CONFIRMATION_TAG = "CONFIRMATION_FRAG";


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

        setSupportActionBar((Toolbar) findViewById(R.id.confirmationToolBar));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            String name = getIntent().getStringExtra(EXTRA_NAME);
            String desc = getIntent().getStringExtra(EXTRA_DESCRIPTION);
            boolean isFavorite = getIntent().getBooleanExtra(EXTRA_FAVORITE, false);
            ConfirmationFragment frag = ConfirmationFragment.newInstance(name, desc, isFavorite);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.confirmationContainer, frag, CONFIRMATION_TAG)
                    .commit();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onConfirmationOkClicked(String name, String desc, boolean isFavorite) {
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESCRIPTION, desc);
        data.putExtra(EXTRA_FAVORITE, isFavorite);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
