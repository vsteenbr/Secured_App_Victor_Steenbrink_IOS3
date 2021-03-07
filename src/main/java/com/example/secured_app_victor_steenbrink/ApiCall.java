package com.example.secured_app_victor_steenbrink;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {


    @GET("accounts/{id}")
    Call<Accounts> getData(@Path("id") int id);

}
