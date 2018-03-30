package com.benharvey.stackoverflowusers.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.benharvey.stackoverflowusers.R;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.models.UserListResponse;
import com.benharvey.stackoverflowusers.presenters.UsersListAdapter;
import com.benharvey.stackoverflowusers.presenters.UsersListPresenter;

import java.util.List;

public class UsersListActivity extends AppCompatActivity implements UsersListView {

    private UsersListPresenter usersListPresenter = new UsersListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        usersListPresenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        usersListPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        usersListPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersListPresenter.onDestroy();
    }

    @Override
    public void displayUsersList(UserListResponse userListResponse) {
        List<User> userList = userListResponse.getItems();

        int i=3;
        for(User user : userList){
            Log.e("benmark", String.valueOf(i));
            i++;
        }
        RecyclerView rv= (RecyclerView) findViewById(R.id.usersListRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new UsersListAdapter(this, userList));
    }
}
