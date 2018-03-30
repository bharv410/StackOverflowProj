package com.benharvey.stackoverflowusers.presenters;

/**
 * Created by benharvey on 3/29/18.
 */

public interface Presenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

}