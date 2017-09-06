package com.youngindians.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.youngindians.Adapters.MembersListAdapter;
import com.youngindians.CallBacks.HTTPCallback;
import com.youngindians.CallBacks.MemberListAdapterCallBack;
import com.youngindians.Communication.WebServerCommunication;
import com.youngindians.Models.User;
import com.youngindians.R;
import com.youngindians.Utils.CommonFunctions;
import com.youngindians.Utils.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MemberFragment extends Fragment implements
        HTTPCallback,
        MemberListAdapterCallBack{

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private Context context;
    private MembersListAdapter membersListAdapter;
    private ArrayList<User> userArrayList;
    private MaterialEditText memberListSearchET;


    // Dialogs
    private AlertDialog.Builder memberInfoDialogBuilder;
    private View memberInfoDialogView;
    private AlertDialog memberInfoDialog;

    private TextView
            memberNameTV, memberPositionTV, memberEmailTV, memberBirthdayTV, memberCompanyTV, memberDesignationTV;

    private CommonFunctions commonFunctions;

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

        context = view.getContext();
        commonFunctions = new CommonFunctions(context);

        setViews(view);
        setOnClickListener();

        return view;
    }

    private void setViews(View view){

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        memberListSearchET = (MaterialEditText) view.findViewById(R.id.member_list_search);

        // Dialog
        memberInfoDialogView = getActivity().getLayoutInflater().inflate(R.layout.member_info_dialog_view, null);

        memberNameTV = (TextView) memberInfoDialogView.findViewById(R.id.member_name_text_view_at_member_info_dialog);
        memberPositionTV = (TextView) memberInfoDialogView.findViewById(R.id.member_position_text_view_at_member_info_dialog);
        memberEmailTV = (TextView) memberInfoDialogView.findViewById(R.id.member_email_text_view_at_member_info_dialog);
        memberBirthdayTV = (TextView) memberInfoDialogView.findViewById(R.id.member_birthday_text_view_at_member_info_dialog);
        memberCompanyTV = (TextView) memberInfoDialogView.findViewById(R.id.member_company_text_view_at_member_info_dialog);
        memberDesignationTV = (TextView) memberInfoDialogView.findViewById(R.id.member_designatin_text_view_at_member_info_dialog);

        // Dialog builder
        memberInfoDialogBuilder = new AlertDialog.Builder(context);
        memberInfoDialogBuilder.setView(memberInfoDialogView);
        memberInfoDialogBuilder.setCancelable(true);

        memberInfoDialog = memberInfoDialogBuilder.create();
    }

    private void setOnClickListener() {

        memberListSearchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filterUsers(s.toString().toLowerCase());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });

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
        n.getInformation(Constants.CL_CODE_GET_MEMBERS);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setRecyclerView(ArrayList<User> userArrayList){

        membersListAdapter = new MembersListAdapter(context, userArrayList, new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(User user) {

                new CommonFunctions(context).log("List", user.toString());
            }
        }, this);
        recyclerView.setAdapter(membersListAdapter);
    }

    private void filterUsers(String text){

        ArrayList<User> tempUsers = new ArrayList();

        for(User user: userArrayList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(user.getName().toLowerCase().contains(text)){
                tempUsers.add(user);
            }
        }
        //update recyclerview
        membersListAdapter.updateList(tempUsers);
    }


    private void showMemberInformation(User user){

        memberNameTV.setText(user.getName());

        if(user.getPosition() != null){
            memberPositionTV.setText(user.getPosition());
        }
        memberEmailTV.setText(user.getEmail());
        memberBirthdayTV.setText(commonFunctions.getBirthdayForCards(user.getBirthday()));
        memberCompanyTV.setText(user.getCompany());
        memberDesignationTV.setText(user.getDesignation());

        memberInfoDialog.show();
    }

    /**
     * *****************************************************************
     *
     *   Call backs
     *
     * *****************************************************************
     */

    @Override
    public void onSuccess(String info, int callCode) {

        Gson gson = new Gson();

        if(callCode == Constants.CL_CODE_GET_MEMBERS){

            Type type = new TypeToken<List<User>>(){}.getType();
            userArrayList = gson.fromJson(info, type);
            setRecyclerView(userArrayList);

        }

    }

    @Override
    public void onFailure(int callBackCode, int callCode) {

    }

    @Override
    public void onMemberListItemClick(int position) {

        User user = userArrayList.get(position);
        showMemberInformation(user);
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
