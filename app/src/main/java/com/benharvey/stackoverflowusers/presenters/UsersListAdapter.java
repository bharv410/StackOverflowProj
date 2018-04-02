package com.benharvey.stackoverflowusers.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.benharvey.stackoverflowusers.R;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.views.UserListItemViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
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

        loadProfileImage(holder, user);

        //some ages are null
        if(user.getAge() != null) {
            holder.lastActiveTxt.setText(c.getString(R.string.user_age_text, user.getAge()));
        }else{
            holder.lastActiveTxt.setText(c.getString(R.string.user_age_text_not_available));
        }

        //some locations are null
        if(user.getLocation() != null) {
            holder.locationTxt.setText(user.getLocation());
        }else{
            holder.locationTxt.setText(c.getString(R.string.user_location_not_available));
        }
    }

    private void loadProfileImage(final UserListItemViewHolder holder, final User user){
        Picasso.with(c)
                .load(user.getProfile_image())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.progress_animation)
                .into(holder.img, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(c)
                                .load(user.getProfile_image())
                                .into(holder.img, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}