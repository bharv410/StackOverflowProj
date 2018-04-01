package com.benharvey.stackoverflowusers.presenters;

import android.util.Log;

import com.benharvey.stackoverflowusers.networking.GetUsersService;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.models.UserListResponse;
import com.benharvey.stackoverflowusers.networking.ServiceGenerator;
import com.benharvey.stackoverflowusers.views.UsersListView;

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
    private final String TAG = getClass().getName();

    private UsersListView usersListView;
    private List<User> userList;

    public UsersListPresenter(UsersListView usersListView){
        this.usersListView = usersListView;
        userList = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        refreshUserList();
    }

    private void refreshUserList(){
        GetUsersService getUsersService = ServiceGenerator.createService(GetUsersService.class);

        getUsersService.listUsers().enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                userList = response.body().getItems();
                usersListView.displayUsersList(userList);
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Log.e(TAG, "failure getting users" + t.getMessage());
                usersListView.showRetrievalError();
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
