package com.example.danco.bonus1.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danco.bonus1.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.danco.bonus1.fragment.ConfirmationFragment.ConfirmationFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";
    private static final String ARG_DESC = "description";
    private static final String ARG_FAVORITE = "isFavorite";

    private String name;
    private String description;
    private boolean isFavorite;

    private ConfirmationFragmentListener listener;
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
    public interface ConfirmationFragmentListener {
        // TODO: Update argument type and name
        public void onOkClicked(String name, String desc, boolean isFavorite);
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name grid/image name.
     * @param description a description.
     * @param isFavorite favorite state.
     * @return A new instance of fragment BlankFragment.
     */
    public static ConfirmationFragment newInstance(String name, String description, boolean isFavorite) {
        ConfirmationFragment fragment = new ConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESC, description);
        args.putBoolean(ARG_FAVORITE, isFavorite);
        fragment.setArguments(args);
        return fragment;
    }


    public ConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            description = getArguments().getString(ARG_DESC);
            isFavorite = getArguments().getBoolean(ARG_FAVORITE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        holder.okButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "User clicked ok", Toast.LENGTH_SHORT).show();
        listener.onOkClicked(name, description, isFavorite);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ConfirmationFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ConfirmationFragmentListener");
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


    /* package */ static class ViewHolder {
        final TextView nameView;
        final TextView descriptionView;
        final TextView isFavoriteView;
        final Button okButton;

        ViewHolder(View view) {
            nameView = (TextView) view.findViewById(R.id.gridNameValue);
            descriptionView = (TextView) view.findViewById(R.id.gridDescriptionValue);
            isFavoriteView = (TextView) view.findViewById(R.id.gridFavoriteValue);
            okButton = (Button) view.findViewById(R.id.okButton);
        }
    }
}
