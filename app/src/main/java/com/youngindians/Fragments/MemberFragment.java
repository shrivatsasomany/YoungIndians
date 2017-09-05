package com.youngindians.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youngindians.Adapters.MembersListAdapter;
import com.youngindians.Communication.HTTPCallback;
import com.youngindians.Communication.WebServerCommunication;
import com.youngindians.Models.Event;
import com.youngindians.Models.User;
import com.youngindians.R;
import com.youngindians.Fragments.dummy.DummyContent;
import com.youngindians.Fragments.dummy.DummyContent.DummyItem;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MemberFragment extends Fragment implements HTTPCallback {


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private Context context;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MemberFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MemberFragment newInstance(int columnCount) {
        MemberFragment fragment = new MemberFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_list, container, false);
        // Set the adapter
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        WebServerCommunication n = new WebServerCommunication(this, getContext());
        n.getUsers();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSuccess(Object o) {
        if(o instanceof ArrayList) {
            ArrayList<User> users = (ArrayList<User>) o;
            recyclerView.setAdapter(new MembersListAdapter(context, users, new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(User user) {
                    Log.d("List", user.toString());
                }
            }));

        } else if(o instanceof String) {
            Log.d("Error", (String)o);
        }

    }

    @Override
    public void onFailure(String error) {

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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(User user);
    }
}
