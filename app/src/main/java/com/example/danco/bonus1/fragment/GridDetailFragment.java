package com.example.danco.bonus1.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danco.bonus1.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.danco.bonus1.fragment.GridDetailFragment.OnSubmitDetailsListener} interface
 * to handle interaction events.
 * Use the {@link GridDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridDetailFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_FAVORITE = "favorite";

    private String name;
    private String description;
    private boolean isFavorite;

    private OnSubmitDetailsListener listener;
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
    public interface OnSubmitDetailsListener {
        public void onSubmitDetails(String description, boolean isFavorite);
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name name of the picture.
     * @return A new instance of fragment GridDetailFragment.
     */
    public static GridDetailFragment newInstance(String name) {
        return newInstance(name, "", false);
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name name of the picture.
     * @param description a descriptionView.
     * @param favoriteState true if indicated as a favorite.
     * @return A new instance of fragment GridDetailFragment.
     */
    public static GridDetailFragment newInstance(String name, String description, boolean favoriteState) {
        GridDetailFragment fragment = new GridDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESCRIPTION, description);
        args.putBoolean(ARG_FAVORITE, favoriteState);
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
            name = getArguments().getString(ARG_NAME);
            description = getArguments().getString(ARG_DESCRIPTION);
            isFavorite = getArguments().getBoolean(ARG_FAVORITE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        holder.submitButton.setOnClickListener(this);
        holder.isFavorite.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.isFavoriteGrid) {
            CheckedTextView checkedTextView = (CheckedTextView)
                    getActivity().findViewById(R.id.isFavoriteGrid);
            checkedTextView.setChecked(!checkedTextView.isChecked());
            return;
        }

        if (listener != null) {
            description = getViewHolder().descriptionView.getText().toString();
            isFavorite = getViewHolder().isFavorite.isChecked();
            listener.onSubmitDetails(description, isFavorite);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        ViewHolder holder = getViewHolder();
        updateView(holder);
    }


    @Override
    public void onPause() {
        ViewHolder holder = getViewHolder();

        description = holder.descriptionView.getText().toString();
        isFavorite = holder.isFavorite.isChecked();

        super.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_DESCRIPTION, description);
        outState.putBoolean(ARG_FAVORITE, isFavorite);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            description = savedInstanceState.getString(ARG_DESCRIPTION);
            isFavorite = savedInstanceState.getBoolean(ARG_FAVORITE);
        }
    }


    private void updateView(ViewHolder holder) {
        if (holder == null) {
            return;
        }

        holder.nameView.setText(name);
        holder.descriptionView.setText(description);
        holder.isFavorite.setChecked(isFavorite);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnSubmitDetailsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSubmitDetailsListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    private ViewHolder getViewHolder() {
        View view = getView();
        return view != null ? (ViewHolder) view.getTag() : null;
    }

    static class ViewHolder {
        final TextView nameView;
        final EditText descriptionView;
        final CheckedTextView isFavorite;
        final Button submitButton;

        ViewHolder(View view) {
            nameView = (TextView) view.findViewById(R.id.gridItemName);
            descriptionView = (EditText) view.findViewById(R.id.gridItemDescription);
            isFavorite = (CheckedTextView) view.findViewById(R.id.isFavoriteGrid);
            submitButton = (Button) view.findViewById(R.id.submitButton);
        }
    }
}
