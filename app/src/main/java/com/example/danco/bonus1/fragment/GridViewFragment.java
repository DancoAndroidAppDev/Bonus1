package com.example.danco.bonus1.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danco.bonus1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GridViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridViewFragment extends Fragment {

    private static final String GRID_NAME = "gridName";

    // Providing sample data for the grid
    private static final ArrayList<String> data = new ArrayList<>(30);

    static {
        for (int i = 0; i < 30; ++i) {
            data.add("Grid Item " + (i + 1));
        }
    }


    private String gridName;

    private OnFragmentInteractionListener listener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment GridViewFragment.
     */
    public static GridViewFragment newInstance(String name) {
        GridViewFragment fragment = new GridViewFragment();
        Bundle args = new Bundle();
        args.putString(GRID_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }


    public GridViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gridName = getArguments().getString(GRID_NAME);
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

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


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
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
