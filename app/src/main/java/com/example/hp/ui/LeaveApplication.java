package com.example.hp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LeaveApplication extends AppCompatActivity {

    TextView tvname,tvrolln,tvdate;
    EditText etreason;
    Button btsubmitl,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);


        final Retrofit retrofit = new RetrofitConfig().config();

        tvname = (TextView) findViewById(R.id.tvnamel);
        tvrolln = (TextView) findViewById(R.id.tvrollnl);
        tvdate = (TextView) findViewById(R.id.tvdatel);
        etreason = (EditText) findViewById(R.id.etreason);
        btsubmitl = (Button) findViewById(R.id.btsubmitleave);
        btnback = (Button) findViewById(R.id.btback);


        final int rollno =getIntent().getIntExtra("rollno",-1);
        final String name = getIntent().getStringExtra("name");
        final String email = getIntent().getStringExtra("email");
        final String division = getIntent().getStringExtra("division");
        tvname.setText(name);
        tvrolln.setText(""+rollno);
        String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
        tvdate.setText(date1);

        btsubmitl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( etreason.getText().toString().isEmpty())
                {

                    Toast.makeText(LeaveApplication.this,"Fill All Details",Toast.LENGTH_LONG).show();
                }
                else {


                    API api = retrofit.create(API.class);
                    Call <UserModel> call = api.submitLeave(Integer.parseInt(tvrolln.getText().toString()),
                            Date.valueOf(tvdate.getText().toString()),etreason.getText().toString());
                    call.enqueue(new Callback <UserModel>() {
                        @Override
                        public void onResponse(Call <UserModel> call, Response <UserModel> response) {

                        }

                        @Override
                        public void onFailure(Call <UserModel> call, Throwable t) {

                        }
                    });
                    Intent i = new Intent(LeaveApplication.this, Activity2.class);
                    i.putExtra("rollno", rollno);
                    i.putExtra("name", name);
                    i.putExtra("email", email);
                    i.putExtra("division", division);
                    Toast.makeText(LeaveApplication.this,"Submitted..!",Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LeaveApplication.this,Activity2.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                startActivity(i);
            }
        });
    }
}


