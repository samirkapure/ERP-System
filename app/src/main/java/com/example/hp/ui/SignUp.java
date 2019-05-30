package com.example.hp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignUp extends AppCompatActivity {

    private Spinner div1;
    private EditText rollno,name,email,password,division;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final Retrofit retrofit = new RetrofitConfig().config();

        rollno = (EditText) findViewById(R.id.etrollnosu);
        name = (EditText) findViewById(R.id.etnamesu);
        email = (EditText) findViewById(R.id.etemailsu);
        password = (EditText) findViewById(R.id.etpasswsu);
        register = (Button) findViewById(R.id.btregistersu);
        div1 = (Spinner) findViewById(R.id.spdiv);
        //division
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.div_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        div1.setAdapter(adapter);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rollno.getText().toString().isEmpty() || name.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty() || div1.getSelectedItem().toString().equals("-SELECT DIVISION-")
                        || !isEmailValid(email.getText().toString()))
                {

                    Toast.makeText(SignUp.this,"Fill All Details Appropriately ",Toast.LENGTH_LONG).show();
                }
                else {

                    API api = retrofit.create(API.class);
                    Call <UserModel> call = api.register(Integer.parseInt(rollno.getText().toString()), name.getText().toString(),
                            email.getText().toString(), password.getText().toString(), div1.getSelectedItem().toString());


                    call.enqueue(new Callback <UserModel>() {
                        @Override
                        public void onResponse(Call <UserModel> call, Response <UserModel> response) {

                            String s=response.body().getName();
                            if(s.equals("Registration failed!")) {
                                Toast.makeText(SignUp.this, s, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(SignUp.this,s,Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, MainActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call <UserModel> call, Throwable t) {


                        }
                    });

                }
            }
        });


    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
