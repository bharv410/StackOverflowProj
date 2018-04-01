package com.benharvey.stackoverflowusers.views;

import android.support.v7.widget.RecyclerView;

import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.models.UserListResponse;

import java.util.List;

/**
 * Created by benharvey on 3/29/18.
 */

public interface UsersListView {
    void displayUsersList(List<User> userList);
    void showRetrievalError();
}
