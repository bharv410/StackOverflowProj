package com.benharvey.stackoverflowusers.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.benharvey.stackoverflowusers.R;
import com.benharvey.stackoverflowusers.models.User;
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
    public void displayUsersList(List<User> userList) {
        RecyclerView rv= findViewById(R.id.usersListRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new UsersListAdapter(this, userList));

        //animate
        rv.scheduleLayoutAnimation();
        rv.invalidate();

        ProgressBar progressBar = findViewById(R.id.loadUsersProgressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayOfflineModeSnackbar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.mainLayout), getString(R.string.offline_snackbar_message), Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void showRetrievalError() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.offline_dialog_title));
        alertDialog.setMessage(getString(R.string.offline_dialog_message));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.dialog_ok_text),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        usersListPresenter.onCreate();
                    }
                });
        alertDialog.show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
