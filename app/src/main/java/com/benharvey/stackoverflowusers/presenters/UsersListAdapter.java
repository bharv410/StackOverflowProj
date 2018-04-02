package com.benharvey.stackoverflowusers.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benharvey.stackoverflowusers.R;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.views.UserListItemViewHolder;
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

        if(user.getAge() != null) {
            holder.lastActiveTxt.setText(user.getAge());
        }else{
            holder.lastActiveTxt.setText("N/A");
        }

        Log.e("benmark", String.valueOf(user.getLast_access_date()));

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

    private String getFormattedDateString(String millisecondString){

        String lastActiveString = DateUtils.getRelativeDateTimeString(

                c, // Suppose you are in an activity or other Context subclass

                Long.parseLong(millisecondString), // The time to display

                DateUtils.SECOND_IN_MILLIS, // The resolution. This will display only
                // minutes (no "3 seconds ago")


                DateUtils.WEEK_IN_MILLIS, // The maximum resolution at which the time will switch
                // to default date instead of spans. This will not
                // display "3 weeks ago" but a full date instead

                0).toString(); // Eventual flags
        Log.e("benmark", lastActiveString);
        return lastActiveString;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}