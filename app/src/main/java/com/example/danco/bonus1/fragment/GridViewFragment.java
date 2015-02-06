package com.example.danco.bonus1.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.danco.bonus1.R;
import com.example.danco.bonus1.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.danco.bonus1.fragment.GridViewFragment.GridViewFragmentListener} interface
 * to handle interaction events.
 * Use the {@link GridViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_VALUES = "initialValues";
    private static final String ARGS_CHOICE_MODE = "gridChoiceMode";
    private static final String STATE_VALUES = GridViewFragment.class.getSimpleName() + ".values";
    private static final String STATE_SELECTION = GridViewFragment.class.getSimpleName() + ".selection";

    // Providing sample data for the grid
    private static ArrayList<String> data = new ArrayList<>();

    private int gridChoiceMode = GridView.CHOICE_MODE_NONE;

    private int selectedItemIndex = 0;

    private GridViewFragmentListener listener;
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface GridViewFragmentListener {
        public void onGridItemSelected(final String gridName);
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param data the data in the grid.
     * @param highlightList
     * @return A new instance of fragment GridViewFragment.
     */
    public static GridViewFragment newInstance(List<String> data, boolean highlightList) {
        GridViewFragment fragment = new GridViewFragment();
        Bundle args = new Bundle();
        String[] dataArr = data.toArray(new String[0]);
        args.putStringArray(ARG_VALUES, dataArr);
        args.putInt(ARGS_CHOICE_MODE, highlightList
                ? GridView.CHOICE_MODE_SINGLE
                : GridView.CHOICE_MODE_NONE);
        fragment.setArguments(args);
        return fragment;
    }


    public GridViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            gridChoiceMode = args.getInt(ARGS_CHOICE_MODE, GridView.CHOICE_MODE_NONE);
            if (savedInstanceState == null) {
                data.addAll(Arrays.asList(args.getStringArray(ARG_VALUES)));
            }
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Fragment parent = getParentFragment();
        Object obj = parent != null ? parent : activity;
        try {
            listener = (GridViewFragmentListener) obj;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement GridViewFragmentListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_view, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        if (savedInstanceState != null) {
            data = savedInstanceState.getStringArrayList(STATE_VALUES);
            selectedItemIndex = savedInstanceState.getInt(STATE_SELECTION, 0);
        }

        holder.grid.setOnItemClickListener(this);

        //shouldn't ever be null... probably don't need this test
        if (data != null) {
            configureAdapter(holder.grid);
        }
    }


    private void configureAdapter(final GridView grid) {
        GridAdapter adapter = new GridAdapter(getActivity(), data);
        grid.setAdapter(adapter);
        grid.setChoiceMode(gridChoiceMode);
        grid.setItemChecked(selectedItemIndex, true);
    }


    public void setValues(List<String> values) {
        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException("values must be 1 or more items long");
        }

        // Ensure values are not null.
        data.clear();
        data.addAll(values);

        ViewHolder holder = getViewHolder();
        if (holder != null) {
            configureAdapter(holder.grid);
        }
    }


    public void setHighlightList(boolean multiFragment) {
        gridChoiceMode = multiFragment ? GridView.CHOICE_MODE_SINGLE : GridView.CHOICE_MODE_NONE;

        // If view not initialized this will happen later.
        ViewHolder holder = getViewHolder();
        if (holder == null || data == null) return;

        holder.grid.setChoiceMode(gridChoiceMode);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STATE_VALUES, data);
        outState.putInt(STATE_SELECTION, selectedItemIndex);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedItemIndex = position;
        GridView grid = (GridView) parent;
        grid.setItemChecked(position, true);
        listener.onGridItemSelected(data.get(position));
    }


    private ViewHolder getViewHolder() {
        View view = getView();
        return view != null ? (ViewHolder) view.getTag() : null;
    }


    static class ViewHolder {
        final GridView grid;

        ViewHolder(View view) {
            grid = (GridView) view.findViewById(R.id.grid);
        }
    }
}
