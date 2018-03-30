package com.benharvey.stackoverflowusers.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.benharvey.stackoverflowusers.R;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.views.UserListItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benharvey on 3/29/18.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UserListItemViewHolder> {

    Context c;
    List<User> users;


    public UsersListAdapter(Context c, List<User> users) {
        this.c = c;
        this.users = users;
    }

    //VIEW ITEM
    @Override
    public UserListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.user_list_item,parent,false);
        return new UserListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserListItemViewHolder holder, int position) {


        //CURRENT SPACECRAFT
        User user =users.get(position);

        //BIND DATA
        holder.nameTxt.setText("name");
        holder.propellantTxt.setText("propllenat");
        //holder.img.setImageResource(s.getImage());


    }

    //TOTAL SPACECRAFTS
    @Override
    public int getItemCount() {
        return 7;
    }
}