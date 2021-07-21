package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Adapter.RecyclerViewClickListener {
    private RecyclerView tajmerRv;
    private ProgressBar progressBar;
    private Adapter.RecyclerViewClickListener listener;
    private int i = 0, timeChosen, countDownInterval = 1000, countDownTime, counter321 = 3;
    int[] opcije = {1, 3, 5, 10, 30, 60};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        TextView counter = findViewById(R.id.tv_counterDisplay);
        TextView countdown = findViewById(R.id.tv_321countdown);
        ImageView cookie = findViewById(R.id.img_cookie);


        countdown.setVisibility(View.INVISIBLE);

        cookie.setEnabled(false);
        cookie.setClickable(true);
        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                counter.setText("" + i);
            }
        });

        setRecyclerView();  // RecyclerView


    }


    public void setRecyclerView() {
        tajmerRv = findViewById(R.id.recyclerView);
        Adapter.RecyclerViewClickListener listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onButtonClick(View v, int position) {
                TextView lastScore = findViewById(R.id.tv_lastScore);
                TextView counter = findViewById(R.id.tv_counterDisplay);
                counter.setText("0");

                ProgressBar pb = findViewById(R.id.progressBar);
                ImageView cookie = findViewById(R.id.img_cookie);
                cookie.setEnabled(false);
                TextView tv = findViewById(R.id.tv_321countdown);
                tv.setVisibility(View.VISIBLE);

                CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                    int i = 3;

                    @Override
                    public void onTick(long millisUntilFinished) {
                        tv.setText("" + i);
                        i--;
                    }

                    @Override
                    public void onFinish() {
                        tv.setVisibility(View.INVISIBLE);
                        cookie.setEnabled(true);

                    }
                };
                countDownTimer.start();


                Runnable run = new Runnable() {
                    @Override
                    public void run() {

                        countDownTime = opcije[position];
                        timeChosen = opcije[position];
                        pb.setMax(timeChosen);


                        i = 0;
                        cookie.setEnabled(true);
                        CountDownTimer timer = new CountDownTimer(countDownTime * 1000, countDownInterval) {


                            @Override
                            public void onTick(long millisUntilFinished) {

                                progressBar = findViewById(R.id.progressBar);
                                progressBar.setProgress(timeChosen);
                                timeChosen--;

                            }

                            @Override
                            public void onFinish() {
                                ImageView cookie = findViewById(R.id.img_cookie);
                                cookie.setEnabled(false);
                                progressBar.setProgress(0);
                                lastScore.setText("Last score = " + i);


                            }
                        };

                        timer.start();

                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(run, 3000);

            }
        };

        Adapter adapter = new Adapter(opcije, listener);
        tajmerRv.setAdapter(adapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tajmerRv.setLayoutManager(linearLayout);

    }


    @Override
    public void onButtonClick(View v, int position) {
    }
}