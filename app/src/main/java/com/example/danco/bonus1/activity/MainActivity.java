package com.example.danco.bonus1.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.example.danco.bonus1.R;
import com.example.danco.bonus1.fragment.GridViewFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements GridViewFragment.GridViewFragmentListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String STATE_SELECTED_POSITION = "selectedPosition";
    private static final String FRAG_NAME = "gridFragment";

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

        haveDetailFragment = findViewById(R.id.gridDetailContainer) != null;

        Toolbar toolbar = (Toolbar) findViewById(R.id.topLevelToolBar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            selectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, selectedPosition);
        } else {
            Fragment fragment = GridViewFragment.newInstance(data, true);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.gridViewFragment, fragment, FRAG_NAME)
                    .commit();
        }
    }


    @Override
    public void onFragmentInteraction(final String gridName) {
        //start detail activity for provided name
        Log.i(TAG, String.format("Grid item named: %s clicked", gridName));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
