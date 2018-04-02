package com.benharvey.stackoverflowusers.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benharvey on 3/30/18.
 */

public class UserListResponse {

    @SerializedName("items")
    List<User> items;

    public List<User> getItems() {
        return items;
    }

    public boolean hasItems(){
        return (items != null) && items.size()>0;
    }
}
