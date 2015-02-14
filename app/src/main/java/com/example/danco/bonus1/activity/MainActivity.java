package com.example.danco.bonus1.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.opengl.ETC1;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.example.danco.bonus1.R;
import com.example.danco.bonus1.fragment.GridDetailFragment;
import com.example.danco.bonus1.fragment.GridViewFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements GridViewFragment.GridViewFragmentListener,
        GridDetailFragment.GridDetailListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String STATE_SELECTED_POSITION = "selectedPosition";
    private static final String DETAIL_FRAGMENT = "detailFragment";
    private static final int UPDATE_DETAILS = 200;
    private static final int CONFIRM_DETAILS = 300;

    private boolean haveDetailFragment = false;
    private int selectedPosition = 1;

    private static final ArrayList<String> data = new ArrayList<>(30);

    static {
        for (int i = 0; i < 30; ++i) {
            data.add("Grid Item " + (i + 1));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.topLevelToolBar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(GridDetailFragment.ARG_NAME);
            String desc = savedInstanceState.getString(GridDetailFragment.ARG_DESCRIPTION);
            boolean isFavorite = savedInstanceState.getBoolean(GridDetailFragment.ARG_FAVORITE);

            Log.i(TAG + ".onCreate", String.format("Name = %s, desc = %s, favorite = %s",
                    name, desc, isFavorite));


            if (!getResources().getBoolean(R.bool.multiFragment)) {
                Intent intent = GridDetailActivity.buildIntent(this, name, desc, isFavorite);
                startActivityForResult(intent, UPDATE_DETAILS);
                return;
            }
        }

        haveDetailFragment = findViewById(R.id.gridDetailContainer) != null;

        GridViewFragment fragment = (GridViewFragment)
                getSupportFragmentManager().findFragmentById(R.id.gridViewFragment);
        fragment.setHighlightList(true);

        if (savedInstanceState == null) {
            fragment.setValues(data);
        }

        if (savedInstanceState != null) {
            selectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, selectedPosition);

            Log.i(TAG + ".onCreate", String.format("SelectedPosition = %d", selectedPosition));
        }

        if (haveDetailFragment) {
            GridDetailFragment frag = (GridDetailFragment)
                    getSupportFragmentManager().findFragmentByTag(DETAIL_FRAGMENT);
            if (frag == null) {
                frag = GridDetailFragment.newInstance(data.get(selectedPosition));
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.gridDetailContainer, frag, DETAIL_FRAGMENT)
                        .commit();
            }
        }
    }


    @Override
    public void onGridItemSelected(final String gridName) {
        //start detail activity for provided name
        Log.i(TAG, String.format("Grid item named: %s clicked", gridName));

        if (haveDetailFragment) {
            GridDetailFragment fragment = GridDetailFragment.newInstance(gridName);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.gridDetailContainer, fragment, DETAIL_FRAGMENT)
                    .commit();
        } else {
            startActivityForResult(GridDetailActivity.buildIntent(this, gridName), UPDATE_DETAILS);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UPDATE_DETAILS && data != null) {
                // not sure what to do with the info from the detail activity...
                Log.i(TAG, String.format("Received description: '%s' and favorite state = %s",
                        data.getStringExtra(GridDetailActivity.EXTRA_DESCRIPTION),
                        data.getBooleanExtra(GridDetailActivity.EXTRA_FAVORITE, false)));
            } else if (requestCode == CONFIRM_DETAILS) {
                Log.i(TAG, "User confirmed details");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onSubmitDetails(String name, String description, boolean isFavorite) {
        Log.i(TAG, "onSubmitDetails");
        Intent confirm = ConfirmationActivity.buildIntent(this, name, description, isFavorite);

        startActivityForResult(confirm, CONFIRM_DETAILS);
    }
}
