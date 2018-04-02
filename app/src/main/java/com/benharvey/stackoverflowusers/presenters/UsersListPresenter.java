package com.benharvey.stackoverflowusers.presenters;

import android.util.Log;

import com.benharvey.stackoverflowusers.networking.GetUsersService;
import com.benharvey.stackoverflowusers.models.User;
import com.benharvey.stackoverflowusers.models.UserListResponse;
import com.benharvey.stackoverflowusers.networking.ServiceGenerator;
import com.benharvey.stackoverflowusers.networking.UserDatabaseAdapter;
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

    public UsersListPresenter(UsersListView usersListView){
        this.usersListView = usersListView;
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
                List<User> userList = response.body().getItems();
                usersListView.displayUsersList(userList);
                saveUsersToLocalDB(userList);
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Log.e(TAG, "failure getting users" + t.getMessage());
                //usersListView.showRetrievalError();
                Log.e("benmark", "FAILED READ FROM DISK");
                attemptToReadFromDisk();
            }
        });
    }

    private void attemptToReadFromDisk(){
        UserDatabaseAdapter userDatabaseAdapter = new UserDatabaseAdapter(usersListView.getContext());
        userDatabaseAdapter = userDatabaseAdapter.open();
        List<User> userListFromDisk = userDatabaseAdapter.getAllUsers();
        if(userListFromDisk.size() > 0){
            Log.e("benmark", "OMG IS GREATER THAN 0");
            usersListView.displayUsersList(userListFromDisk);
        }else{
            Log.e("benmark", "awww man");
            usersListView.showRetrievalError();
        }
    }

    private void saveUsersToLocalDB(List<User> userList){
        UserDatabaseAdapter userDatabaseAdapter = new UserDatabaseAdapter(usersListView.getContext());
        userDatabaseAdapter = userDatabaseAdapter.open();
        for(User user : userList){
            String recieveOk = userDatabaseAdapter.
                    insertEntry(user.getDisplay_name(),
                            user.getBadgeCount().getGold(),
                            user.getBadgeCount().getSilver(),
                            user.getBadgeCount().getBronze(),
                            user.getProfile_image(),
                            user.getAge(),
                            user.getLocation());
            Log.e("benmark", recieveOk);
        }
        userDatabaseAdapter.close();
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
