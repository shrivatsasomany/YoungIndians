package com.youngindians.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youngindians.CallBacks.MemberListAdapterCallBack;
import com.youngindians.Fragments.MemberFragment.OnListFragmentInteractionListener;
import com.youngindians.Fragments.dummy.DummyContent.DummyItem;
import com.youngindians.Models.User;
import com.youngindians.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MembersListAdapter extends RecyclerView.Adapter<MembersListAdapter.UserViewHolder> {

    private ArrayList<User> users;
    private final OnListFragmentInteractionListener mListener;
    private Context context;
    private MemberListAdapterCallBack callBack;

    public MembersListAdapter(Context context, ArrayList<User> users, OnListFragmentInteractionListener listener, MemberListAdapterCallBack callBack) {
            this.users = users;
        mListener = listener;
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        holder.mItem = users.get(position);
        if(holder.mItem.getImage().equals("")) {
            holder.mItem.setImage("http://xyz.zyx/poop.jpg");
        }
        Picasso.with(context).load(holder.mItem.getImage()).placeholder(R.drawable.female_profile_generic).into(holder.profileImage);
        holder.memberName.setText(holder.mItem.getName());
        holder.memberType.setText(holder.mItem.getUser_type());
        holder.memberJob.setText(holder.mItem.getDesignation()+" – "+holder.mItem.getCompany());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    callBack.onMemberListItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public User mItem;
        public final View mView;
        public final CircleImageView profileImage;
        public final TextView memberName;
        public final TextView memberType;
        public final TextView memberJob;

        public UserViewHolder(View view) {
            super(view);
            mView = view;
            profileImage = (CircleImageView) view.findViewById(R.id.member_list_profile_image);
            memberName = (TextView)view.findViewById(R.id.member_list_item_name);
            memberType = (TextView)view.findViewById(R.id.member_list_item_type);
            memberJob = (TextView)view.findViewById(R.id.member_list_item_job);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + memberName.getText() + "'";
        }
    }

    public void updateList(ArrayList<User> list){
        users = list;
        notifyDataSetChanged();
    }
}
