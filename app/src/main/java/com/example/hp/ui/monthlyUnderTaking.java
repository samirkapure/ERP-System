package com.example.hp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class monthlyUnderTaking extends AppCompatActivity {

    private TextView tv,tvname,tvdate,tvrn;
    private CheckBox accept;
    private Button submitmu,bakbtnmu;
    private Spinner spmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_under_taking);

        final Retrofit retrofit = new RetrofitConfig().config();



        tvrn= (TextView) findViewById(R.id.tvrnmu) ;
        tvname = (TextView) findViewById(R.id.tvnamemu);
        tvdate = (TextView)findViewById(R.id.tvdatemu);
        spmonth = (Spinner) findViewById(R.id.spmonthmu);
        String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
        tvdate.setText(date1);

        //month
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmonth.setAdapter(adapter);

        final String email = getIntent().getStringExtra("email");
        final String division = getIntent().getStringExtra("division");
        final int rollno =getIntent().getIntExtra("rollno",-1);
        final String name = getIntent().getStringExtra("name");
        tvname.setText(name);
        tvrn.setText(""+rollno);

        tv = (TextView)findViewById(R.id.tv);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setVerticalScrollBarEnabled(true);

        accept = (CheckBox)findViewById(R.id.cbaccept);
        submitmu = (Button) findViewById(R.id.btnsubitmu);
        submitmu.setEnabled(false);

        //enabling submit only when i accept is checked
        accept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                submitmu.setEnabled(isChecked);
            }
        });


        submitmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spmonth.getSelectedItem().toString().equals("-SELECT MONTH-"))
                    {

                    Toast.makeText(monthlyUnderTaking.this,"Fill All Details",Toast.LENGTH_LONG).show();
                }
                else
                 {

                    API api = retrofit.create(API.class);
                    Call <UserModel> call = api.submitMonthlyu(Integer.parseInt(tvrn.getText().toString()),
                            Date.valueOf(tvdate.getText().toString()), spmonth.getSelectedItem().toString());
                    call.enqueue(new Callback <UserModel>() {
                        @Override
                        public void onResponse(Call <UserModel> call, Response <UserModel> response) {

                        }

                        @Override
                        public void onFailure(Call <UserModel> call, Throwable t) {

                        }
                    });
                    Intent i = new Intent(monthlyUnderTaking.this, Activity2.class);
                     i.putExtra("rollno", rollno);
                     i.putExtra("name", name);
                     i.putExtra("email", email);
                     i.putExtra("division", division);
                     Toast.makeText(monthlyUnderTaking.this,"Submitted..!",Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });




        //back
        bakbtnmu = (Button)findViewById(R.id.backbtmu);
        bakbtnmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(monthlyUnderTaking.this, Activity2.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                startActivity(i);
            }
        });
    }
}
