package com.example.hp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class feedback extends AppCompatActivity {

    private RatingBar rbclassrooms,rblabs,rbcorridors,rbwashrooms,rblibrary,rbcanteen;
    private Button btnsubmitfd,backbtnfd;
    private TextView tvrollnofd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final Retrofit retrofit = new RetrofitConfig().config();

        btnsubmitfd = (Button)findViewById(R.id.btnsubmitfeedback);
        tvrollnofd = (TextView) findViewById(R.id.tvrollnofd);
        rbclassrooms = (RatingBar)findViewById(R.id.rbclassrooms);
        rblabs = (RatingBar)findViewById(R.id.rblabs);
        rbcorridors = (RatingBar)findViewById(R.id.rbcorridors);
        rbwashrooms = (RatingBar)findViewById(R.id.rbwashrooms);
        rblibrary = (RatingBar)findViewById(R.id.rblibrary);
        rbcanteen = (RatingBar)findViewById(R.id.rbcanteen);

        final String name = getIntent().getStringExtra("name");
        final String email = getIntent().getStringExtra("email");
        final String division = getIntent().getStringExtra("division");
        final int rollno =getIntent().getIntExtra("rollno",-1);
        tvrollnofd.setText(""+rollno);
 //--------------------------------------------------------------------------------------------------
        rbclassrooms.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this," "+ ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
        rblabs.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this," "+ ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
        rbcorridors.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this," "+ ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
        rbwashrooms.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this," "+ ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });

        rblibrary.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this," "+ ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
        rbcanteen.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this," "+ ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });
 //---------------------------------------------------------------------------------------------------
        btnsubmitfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                API api = retrofit.create(API.class);
                Call<UserModel> call = api.submitFeedback(Integer.parseInt(tvrollnofd.getText().toString()),
                        rbclassrooms.getRating(),rblabs.getRating(),rbcorridors.getRating(),rbwashrooms.getRating(),
                        rblibrary.getRating(),rbcanteen.getRating());
                call.enqueue(new Callback <UserModel>() {
                    @Override
                    public void onResponse(Call <UserModel> call, Response<UserModel> response) {

                    }

                    @Override
                    public void onFailure(Call <UserModel> call, Throwable t) {

                    }
                });

                Intent i = new Intent(feedback.this, Activity2.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                Toast.makeText(feedback.this,"Submitted..!",Toast.LENGTH_SHORT).show();
                startActivity(i);



            }
        });
        //back
        backbtnfd=(Button)findViewById(R.id.backbtnfd);
        backbtnfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(feedback.this, Activity2.class);
                i.putExtra("rollno", rollno);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("division", division);
                startActivity(i);
            }
        });

    }
}
