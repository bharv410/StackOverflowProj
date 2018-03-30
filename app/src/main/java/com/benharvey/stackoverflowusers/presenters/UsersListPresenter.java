package com.benharvey.stackoverflowusers.presenters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.benharvey.stackoverflowusers.models.GetUsersService;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.models.UserListResponse;
import com.benharvey.stackoverflowusers.views.UsersListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by benharvey on 3/29/18.
 */

public class UsersListPresenter implements Presenter{

    private UsersListView usersListView;
//    private List<User> userList;


    public UsersListPresenter(UsersListView usersListView){
        this.usersListView = usersListView;
        //userList = new ArrayList<>();
    }
    @Override
    public void onCreate() {
        refreshUserList();
    }

    private void refreshUserList(){
        //userList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetUsersService getUsersService = retrofit.create(GetUsersService.class);

        Call<UserListResponse> call = getUsersService.listUsers();
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                usersListView.displayUsersList(response.body());
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Log.e("benmark", "failure getting users" + t.getMessage());
            }
        });
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
