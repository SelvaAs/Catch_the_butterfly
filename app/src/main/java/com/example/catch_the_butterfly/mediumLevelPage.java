package com.example.catch_the_butterfly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class mediumLevelPage extends AppCompatActivity{


    TextView textViewTime;
    TextView textViewScore;

    ImageView scoreImage;

    int score;

    CountDownTimer countDownTimer;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;

    ImageView imageView13;

    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;


    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_level_page);



        scoreImage=findViewById(R.id.score_image);

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);
        imageView12=findViewById(R.id.imageView12);
        imageView13=findViewById(R.id.imageView13);
        imageView14=findViewById(R.id.imageView14);
        imageView15=findViewById(R.id.imageView15);
        imageView16=findViewById(R.id.imageView16);
        imageView17=findViewById(R.id.imageView17);
        imageView18=findViewById(R.id.imageView18);
        imageView19=findViewById(R.id.imageView19);
        imageView20=findViewById(R.id.imageView20);


        imageArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,
                imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,
                imageView16,imageView17,imageView18,imageView19,imageView20};

        showImagesRandom();


        textViewTime=findViewById(R.id.textViewTime);
        textViewScore=findViewById(R.id.textViewScore);

        score=0;

        countDownTimer=new CountDownTimer(18000,1000) {
            @Override
            public void onTick(long timeUntilFinished) {
                //timeLeftMillis = timeUntilFinished;
                textViewTime.setText("Time : "+timeUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time off");

                handler.removeCallbacks(runnable);

                for (ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(mediumLevelPage.this);
                //alert.setTitle("Restart ? ");
                alert.setMessage("Do you want to play again?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mediumLevelPage.this, "Game Over", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                alert.show();



            }
        };
        countDownTimer.start();



    }

    public void increaseScore(View view){
        score++;
        textViewScore.setText(String.valueOf(score));



    }


    public void showImagesRandom(){
        System.out.println("showImages calisti");

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run()
            {
                for (ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random=new Random();
                int i=random.nextInt(20);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1200);


            }
        };
        handler.post(runnable);

    }


}


