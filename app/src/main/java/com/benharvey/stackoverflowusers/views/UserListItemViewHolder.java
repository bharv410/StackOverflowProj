package com.benharvey.stackoverflowusers.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.benharvey.stackoverflowusers.R;

/**
 * Created by benharvey on 3/29/18.
 */

public class UserListItemViewHolder extends RecyclerView.ViewHolder{

    public TextView nameTxt,propellantTxt;
    public ImageView img;

    public UserListItemViewHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        propellantTxt= (TextView) itemView.findViewById(R.id.propellantTxt);
        img= (ImageView) itemView.findViewById(R.id.spacecraftImg);
    }

}
