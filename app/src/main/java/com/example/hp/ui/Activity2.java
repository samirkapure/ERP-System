package com.example.hp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private Button logout,la,mu,ifd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        logout=(Button)findViewById(R.id.btnlogout);
        la=(Button)findViewById(R.id.btnla);    //leave application
        mu=(Button)findViewById(R.id.btnmu);    //monthly undertaking
        ifd=(Button)findViewById(R.id.btnif);   //infrastructural feedback

        final String email = getIntent().getStringExtra("email");
        final String division = getIntent().getStringExtra("division");
        final int rollno =getIntent().getIntExtra("rollno",-1);
        final String name = getIntent().getStringExtra("name");

        //leave application
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Activity2.this,LeaveApplication.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                startActivity(i);
            }
        });

        //monthly undertaking
        mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  String email = getIntent().getStringExtra("email");
                //String division = getIntent().getStringExtra("division");
                //int rollno =getIntent().getIntExtra("rollno",-1);
                Intent i=new Intent(Activity2.this,monthlyUnderTaking.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                startActivity(i);
            }
        });

        //feedback
        ifd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   String email = getIntent().getStringExtra("email");
               // String division = getIntent().getStringExtra("division");
                //int rollno =getIntent().getIntExtra("rollno",-1);
                Intent i=new Intent(Activity2.this,feedback.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                startActivity(i);
            }
        });


        //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Activity2.this,MainActivity.class);
                startActivity(i);

            }
        });

    }

}
