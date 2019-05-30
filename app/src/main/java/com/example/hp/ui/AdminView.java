package com.example.hp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.adapters.LeaveRecordsAdapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminView extends AppCompatActivity {

    private Button viewla,viewmu,viewfd,logo;
    private ArrayList<UserModel> usermodels;
    private ListView recordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        viewla = (Button)findViewById(R.id.btnviewla);
        viewla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminView.this,ViewLa.class);
                startActivity(i);

            }
        });


        viewmu = (Button)findViewById(R.id.btnviewMu);
        viewmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AdminView.this,ViewMu.class);
                startActivity(i);
            }
        });

        viewfd = (Button)findViewById(R.id.btnviewfd);
        viewfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AdminView.this,ViewFd.class);
                startActivity(i);
            }
        });


        logo = (Button)findViewById(R.id.btnloadv);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminView.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
