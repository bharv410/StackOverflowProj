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

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        goldBadgeTxt = (TextView) itemView.findViewById(R.id.goldBadgeTxt);
        silverBadgeTxt = (TextView) itemView.findViewById(R.id.silverBadgeTxt);
        bronzeBadgeTxt = (TextView) itemView.findViewById(R.id.bronzeBadgeTxt);
        locationTxt = (TextView) itemView.findViewById(R.id.locationTxt);
        lastActiveTxt = (TextView) itemView.findViewById(R.id.lastActiveTxt);
        img = (ImageView) itemView.findViewById(R.id.userListItemGravatar);
    }
}
