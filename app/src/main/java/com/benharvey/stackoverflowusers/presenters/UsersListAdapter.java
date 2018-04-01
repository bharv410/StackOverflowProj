package com.benharvey.stackoverflowusers.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benharvey.stackoverflowusers.R;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.views.UserListItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by benharvey on 3/29/18.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UserListItemViewHolder> {

    private Context c;
    private List<User> users;

    public UsersListAdapter(Context c, List<User> users) {
        this.c = c;
        this.users = users;
    }

    //VIEW ITEM
    @Override
    public UserListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.user_list_item, parent, false);
        return new UserListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserListItemViewHolder holder, int position) {
        User user = users.get(position);

        holder.nameTxt.setText(user.getDisplay_name());
        holder.goldBadgeTxt.setText(user.getBadgeCount().getGold());
        holder.silverBadgeTxt.setText(user.getBadgeCount().getSilver());
        holder.bronzeBadgeTxt.setText(user.getBadgeCount().getBronze());

        //some locations are null
        if(user.getLocation() != null) {
            holder.locationTxt.setText(user.getLocation());
        }else{
            holder.locationTxt.setText("Unknown Location");
        }

        Picasso.get()
                .load(user.getProfile_image())
                .placeholder(R.drawable.progress_animation)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}