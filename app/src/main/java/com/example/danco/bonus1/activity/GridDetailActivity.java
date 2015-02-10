package com.example.danco.bonus1.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.danco.bonus1.R;
import com.example.danco.bonus1.fragment.GridDetailFragment;

public class GridDetailActivity extends ActionBarActivity
        implements GridDetailFragment.GridDetailListener {

    public static final String EXTRA_NAME = GridDetailActivity.class.getSimpleName() + ".name";
    public static final String EXTRA_DESCRIPTION = GridDetailActivity.class.getSimpleName() + ".description";
    public static final String EXTRA_FAVORITE = GridDetailActivity.class.getSimpleName() + ".favorite";

    private static final String FRAG_NAME = "gridDetails";

    public static Intent buildIntent(Context context, String gridName) {
        Intent intent = new Intent(context, GridDetailActivity.class);
        intent.putExtra(EXTRA_NAME, gridName);
        return intent;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean multiFragment = getResources().getBoolean(R.bool.multiFragment);
        if (multiFragment) {
            finish();
            return;
        }

        // phone, or display >720dp wide
        setContentView(R.layout.activity_grid_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.gridDetailToolBar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            String name = getIntent().getStringExtra(EXTRA_NAME);
            GridDetailFragment fragment = GridDetailFragment.newInstance(name);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.gridDetailContainer, fragment, FRAG_NAME)
                    .commit();
        }
    }


    @Override
    public void onSubmitDetails(String name, String description, boolean isFavorite) {
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, getIntent().getExtras().getString(EXTRA_NAME));
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_FAVORITE, isFavorite);
        setResult(Activity.RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
}
