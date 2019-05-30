package com.example.hp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewFd extends AppCompatActivity {


    private TextView classr,lab,corri,washr,libr,cant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fd);

        final Retrofit retrofit = new RetrofitConfig().config();


        classr = (TextView)findViewById(R.id.tvclassr);
        lab = (TextView) findViewById(R.id.tvlabs);
        corri = (TextView)findViewById(R.id.tvcorri);
        washr = (TextView)findViewById(R.id.tvwash);
        libr = (TextView) findViewById(R.id.tvlibrary);
        cant=(TextView) findViewById(R.id.tvcanteen);


        API api = retrofit.create(API.class);

        Call<UserModel> call = api.viewfeedb();
        call.enqueue(new Callback <UserModel>() {
            @Override
            public void onResponse(Call <UserModel> call, Response<UserModel> response) {
                DecimalFormat form = new DecimalFormat("0.00");
                classr.setText(String.valueOf(form.format(response.body().getClassrooms())));
                lab.setText(String.valueOf(form.format(response.body().getLabs())));
                corri.setText(String.valueOf(response.body().getCorridors()));
                washr.setText(String.valueOf(form.format(response.body().getWashrooms())));
                libr.setText(String .valueOf(form.format(response.body().getLibrary())));
                cant.setText(String .valueOf(form.format(response.body().getCanteen())));

            }

            @Override
            public void onFailure(Call <UserModel> call, Throwable t) {

            }
        });
    }
}
