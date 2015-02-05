package com.example.danco.bonus1.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danco.bonus1.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GridDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridDetailFragment extends Fragment {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String FAVORITE = "favorite";

    private String name;
    private String description;
    private boolean isFavorite;

    private OnFragmentInteractionListener listener;
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name name of the picture.
     * @param description a description.
     * @param favoriteState true if indicated as a favorite.
     * @return A new instance of fragment GridDetailFragment.
     */
    public static GridDetailFragment newInstance(String name, String description, boolean favoriteState) {
        GridDetailFragment fragment = new GridDetailFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(DESCRIPTION, description);
        args.putBoolean(FAVORITE, favoriteState);
        fragment.setArguments(args);
        return fragment;
    }


    public GridDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            description = getArguments().getString(DESCRIPTION);
            isFavorite = getArguments().getBoolean(FAVORITE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_detail, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
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


}
