package com.example.hp.ui;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @POST("/student/login.php")
    @FormUrlEncoded
    Call<UserModel> login(@Field("email") String email,
                          @Field("password") String password,
                          @Field("division")String division);

    @GET("/student/leave.php")
    Call<UserModel> submitLeave(@Query("rollno") int rollno,
                          @Query("date")Date date,
                          @Query("reason") String reason);

    @GET("/student/signup.php")
    Call<UserModel> register(@Query("rollno") int rollno,
                             @Query("name") String name,
                             @Query("email") String email,
                             @Query("password") String password,
                             @Query("division") String division);

    @GET("/student/monthlyu.php")
    Call<UserModel> submitMonthlyu(@Query("rollno") int rollno,
                             @Query("date")Date date,
                             @Query("month")String month);

    @POST("/student/viewla.php")
    Call<List<UserModel>> viewlappl();

    @POST("/student/viewmu.php")
    Call<List<UserModel>> viewmappl();

    @GET("/student/feedback.php")
    Call<UserModel> submitFeedback(@Query("rollno") int rollno,
                                   @Query("classrooms")float classrooms,
                                   @Query("labs")float labs,
                                   @Query("corridors")float corridors,
                                   @Query("washrooms")float washrooms,
                                   @Query("library")float library,
                                   @Query("canteen")float canteen  );

    @POST("/student/viewfd.php")
    Call<UserModel> viewfeedb();


}
