package com.benharvey.stackoverflowusers.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.benharvey.stackoverflowusers.R;
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
            public void onResponse(@NonNull Call<UserListResponse> call, @NonNull Response<UserListResponse> response) {
                if(response.isSuccessful()
                        && response.body().hasItems()) {
                    //IF SUCCESSFUL then render list and save to DB (updates duplicate entrys)
                    List<User> userList = response.body().getItems();
                    usersListView.displayUsersList(userList);
                    saveUsersToLocalDB(userList);
                }else{
                    Log.e(TAG, usersListView.getContext().getString(R.string.stack_overflow_api_error));
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                //Failed to read from network. Attempt to read from disk
                Log.e(TAG, t.getLocalizedMessage());
                attemptToReadFromDisk();
            }
        });
    }

    private void attemptToReadFromDisk(){
        UserDatabaseAdapter userDatabaseAdapter = new UserDatabaseAdapter(usersListView.getContext());
        userDatabaseAdapter = userDatabaseAdapter.open();
        List<User> userListFromDisk = userDatabaseAdapter.getFirstPageOfUsers();
        userDatabaseAdapter.close();

        if(userListFromDisk.size() > 0){
            usersListView.displayUsersList(userListFromDisk);
            usersListView.displayOfflineModeSnackbar();
        }else{
            usersListView.showRetrievalError();
        }
    }

    private void saveUsersToLocalDB(List<User> userList){
        UserDatabaseAdapter userDatabaseAdapter = new UserDatabaseAdapter(usersListView.getContext());
        userDatabaseAdapter = userDatabaseAdapter.open();
        for(User user : userList){
            boolean insertSuccesful = userDatabaseAdapter.
                    insertEntry(user.getDisplay_name(),
                            user.getBadgeCount().getGold(),
                            user.getBadgeCount().getSilver(),
                            user.getBadgeCount().getBronze(),
                            user.getProfile_image(),
                            user.getAge(),
                            user.getLocation());
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
