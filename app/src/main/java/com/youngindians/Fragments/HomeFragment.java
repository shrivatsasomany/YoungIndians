package com.youngindians.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.youngindians.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnHomeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {    

    private OnHomeFragmentInteractionListener mListener;

    private View rootView;

    private int position = -1;

    public HomeFragment() {
        // Required empty public constructor
    }
    
    public static HomeFragment newInstance(int position) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ImageView upcomingEventMap = (ImageView)rootView.findViewById(R.id.upcoming_event_map);
        final ProgressBar mapLoader = (ProgressBar)rootView.findViewById(R.id.upcoming_event_map_progress);
        String mapKey = getString(R.string.api_key);
        Log.d("Home", "https://maps.googleapis.com/maps/api/staticmap?key="+mapKey+"&center=28.6139390,77.2090210");
        Picasso.with(getContext())
                .load("https://maps.googleapis.com/maps/api/staticmap?key="+mapKey+"&center=28.6139390,77.2090210&zoom=15&size=500x400&maptype=roadmap&markers=color:red%7C28.6139390,77.2090210")
                .into(upcomingEventMap, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {
                        mapLoader.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onHomeFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentInteractionListener) {
            mListener = (OnHomeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHomeFragmentInteractionListener {
        void onHomeFragmentInteraction();
    }
}
