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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button login, signup;
    private EditText mail,pw;
    private Spinner div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Retrofit retrofit = new RetrofitConfig().config();



        signup = (Button)findViewById(R.id.btnsignup);
        login=(Button)findViewById(R.id.btnlogin);
        mail=(EditText) findViewById(R.id.emailid);
        pw=(EditText) findViewById(R.id.passid);
        div = (Spinner) findViewById(R.id.sp_class);

        //drop down list for choosing class
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        div.setAdapter(adapter);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
               progressDialog.setMessage("Veryfying...");
               progressDialog.setCancelable(false);
               progressDialog.show();

               API api = retrofit.create(API.class);

               Call<UserModel> call = api.login(mail.getText().toString(), pw.getText().toString(),div.getSelectedItem().toString());

               call.enqueue(new Callback <UserModel>() {
                   @Override
                   public void onResponse(Call <UserModel> call, Response<UserModel> response) {
                       progressDialog.dismiss();
                       Log.d("Response", response.toString());
                       String name = response.body().getName();
                       String email = response.body().getEmail();
                       String division = response.body().getDivision();
                       int rollno = response.body().getRollno();

                       if(name.equals("Admin")&& email.equals("admin@gmail.com"))
                       {
                           Intent i=new Intent(MainActivity.this , AdminView.class);
                           i.putExtra("rollno",rollno);
                           i.putExtra("name", name);
                           i.putExtra("email", email);
                           i.putExtra("division", division);
                           startActivity(i);
                       }
                       else {

                           Intent i=new Intent(MainActivity.this , Activity2.class);
                           i.putExtra("rollno",rollno);
                           i.putExtra("name", name);
                           i.putExtra("email", email);
                           i.putExtra("division", division);
                           startActivity(i);
                       }
                   }

                   @Override
                   public void onFailure(Call <UserModel> call, Throwable t) {
                       progressDialog.dismiss();
                       Log.d("Response", t.toString());
                       Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                   }
               });
           }
       });

    }
}
