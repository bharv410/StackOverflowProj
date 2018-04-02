package com.benharvey.stackoverflowusers.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.benharvey.stackoverflowusers.R;

/**
 * Created by benharvey on 3/29/18.
 */

public class UserListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTxt, goldBadgeTxt, silverBadgeTxt, bronzeBadgeTxt, locationTxt, lastActiveTxt;
    public ImageView img;

    public UserListItemViewHolder(View itemView) {
        super(itemView);

        nameTxt = itemView.findViewById(R.id.nameTxt);
        goldBadgeTxt = itemView.findViewById(R.id.goldBadgeTxt);
        silverBadgeTxt = itemView.findViewById(R.id.silverBadgeTxt);
        bronzeBadgeTxt = itemView.findViewById(R.id.bronzeBadgeTxt);
        locationTxt = itemView.findViewById(R.id.locationTxt);
        lastActiveTxt = itemView.findViewById(R.id.lastActiveTxt);
        img = itemView.findViewById(R.id.userListItemGravatar);
    }
}
