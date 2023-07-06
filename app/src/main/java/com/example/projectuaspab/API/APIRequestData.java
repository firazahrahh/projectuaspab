package com.example.projectuaspab.API;

import com.example.projectuaspab.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("link_foto") String link_foto,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("pemeran") String pemeran,
            @Field("jumlah_episode") String jumlah_episode,
            @Field("ulasan") String ulasan
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("link_foto") String link_foto,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("pemeran") String pemeran,
            @Field("jumlah_episode") String jumlah_episode,
            @Field("ulasan") String ulasan
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ModelResponse> ardLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
