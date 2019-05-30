package com.example.hp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hp.adapters.LeaveRecordsAdapter;
import com.example.hp.adapters.MonthlyURecordsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewMu extends AppCompatActivity {

    private ArrayList<UserModel> usermodels;
    private ListView recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mu);

            recordList = findViewById(R.id.muRecords);
            usermodels = new ArrayList();

            final Retrofit retrofit = new RetrofitConfig().config();

            API api = retrofit.create(API.class);
            Call<List<UserModel>> call = api.viewmappl();
            call.enqueue(new Callback<List<UserModel>>() {
                @Override
                public void onResponse(Call <List<UserModel>> call, Response<List<UserModel>> response) {

                    for(int i = 0; i < response.body().size(); i++){
                        UserModel model = new UserModel();
                        model.setRollno(response.body().get(i).getRollno());
                        model.setName(response.body().get(i).getName());
                        model.setDivision(response.body().get(i).getDivision());
                        model.setDate(response.body().get(i).getDate());
                        model.setMonth(response.body().get(i).getMonth());

                        usermodels.add(model);
                    }

                    recordList.setAdapter(new MonthlyURecordsAdapter(ViewMu.this, usermodels));
                }

                @Override
                public void onFailure(Call <List<UserModel>> call, Throwable t) {

                }
            });






        }
    }

