package com.benharvey.stackoverflowusers.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by benharvey on 3/29/18.
 */

public interface GetUsersService {
    @GET("users?site=stackoverflow")
    Call<UserListResponse> listUsers();
}
